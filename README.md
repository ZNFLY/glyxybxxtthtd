# 桂林医学院保修系统

## 1、架构设计：

- 注册中心：Eureka
- 服务调用：OpenFeign
- 分布式事务：seata
- 分布式锁：zookeeper

## 2、接口说明书

### 后端接口API和PC端Springbooot+Vue2+ElementUI）

#### 报修单模块   郭乐源

接单人模块   张家维

审核员模块   黄保华

耗材管理模块    张家维

报修类型模块    黄保华

定时任务      张家维

- 签到表
- 分配保修单给接单人

### 移动端（mobile）（Vue2+Vant）

vue2-vant：https://vant-contrib.gitee.io/vant/v2/#/zh-CN/home

学生填写申报界面    郭乐源

接单人接单界面    张家维

审核员审核界面（审核员1和审核员2）   黄保华



## 3、报修单流程

申报人填写报修单（未派单） → 系统（每 20 分钟）自动派单给接单人（管理员可手动派单）（已派单）→ 接单人收到后开始维修 → 接单人完成维修后，填写耗材和工时（已维修） → 2个审核员审核（已维修） → 接单人确认审核无误（已维修） → 审核员验收（已验收） → 流程结束

## 4、权限

接单人有两个权限：填写耗材和工时、确认审核员的审核结果无误（完成工单）

审核员有两个权限：审核、验收

后台目前有三个权限：手动派单、「审核、验收」、帮助接单人「确认审核员的审核结果无误（完成工单）」

## 5、部署方式一：Docker

### 安装Docker

> 安装软件包

安装 `yum-utils` 包，它提供 `yum-config-manager` 实用程序

```sh
yum install -y yum-utils
```

> 设置存储库

设置稳定版本的仓库（下面二选一）。

官网（国外）

```sh
sudo yum-config-manager \
		--add-repo \
		https://download.docker.com/linux/centos/docker-ce.repo
```

阿里云（国内）

```sh
sudo yum-config-manager \
    	--add-repo \
		http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

阿里云仓库也是定期从官网仓库进行更新引用，两者仓库量无太大区别，如果配置了阿里云仓库，也要配置阿里云镜像加速，即下载速度源由官网改为阿里云。

安装 **最新版本** 的 Docker Engine 和 containerd：

```sh
yum -y install docker-ce docker-ce-cli containerd.io
```

### 验证安装

查看 Docker 版本

```sh
docker version
```

查看 Docker 信息

```sh
docker info
```

### 启动Docker

```sh
systemctl start docker
```

测试是否启动成功

```sh
# 执行该命令
systemctl status docker
```

其他命令：

```sh
# 停止 docker
systemctl stop docker
# 重启 docker
systemctl restart docker
# 设置 docker 服务开启自启动
systemctl enable docker
```

### 创建网络

执行如下命令：

```sh
docker network create web
```

### 安装MySQL

- 首先确保 Docker 服务已经启动。

    ```sh
    docker pull mysql:8.0.27
    ```

- 简单运行 MySQL 服务，将需要挂载的目录拷贝出来

    ```sh
    docker run -d --name mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.27
    ```

    密码自行修改。使用 3307 是因为宿主机可能已经安装了 MySQL，它占用了 3306 端口。

- 创建宿主机的挂载目录

    ```sh
    mkdir -p /docker/mysql
    ```

    拷贝挂载的目录：

    ```sh
    docker cp mysql:/var/lib/mysql /docker/mysql/data
    docker cp mysql:/etc/mysql/conf.d /docker/mysql/conf
    docker cp mysql:/var/log /docker/mysql/log
    ```

    为什么要拷贝出来呢？因为一旦挂载成功，宿主机的挂载目录会覆盖容器的目录，所以我们确保宿主机的挂载目录不能为空，必须要有原来容器目录的文件。

- 宿主机与容器实现挂载，保证数据安全，对应上方拷贝出来的目录

    首先删掉之前的 mysql 容器

    ```sh
    docker rm -f mysql
    ```

    然后创建新的 mysql 容器

    ```sh
    docker run -d \
        --name mysql \
        --restart always \
        -p 3307:3306 \
        -e MYSQL_ROOT_PASSWORD=123456 \
        -v /docker/mysql/data:/var/lib/mysql \
        -v /docker/mysql/conf:/etc/mysql/conf.d \
        -v /docker/mysql/log:/var/log \
        --network web --network-alias mysql \
        mysql:8.0.27
    ```

- 配置 UTF-8 编码

    进入宿主机的配置文件挂载目录：`/docker/mysql/conf`，有一个 mysql.cnf 配置文件，编辑它

    ```sh
    cd /docker/mysql/conf
    
    vim mysql.cnf
    ```

    添加如下内容（如果文件有 `[mysql]`，必须先删除掉）：

    ```sql
    [mysqld]
    character-set-server=utf8
    collation-server=utf8_general_ci
    
    [client]
    default-character-set=utf8
    ```

    ![image-20211211231915077](https://cdn.jsdelivr.net/gh/Kele-Bingtang/static/img/Docker/20211211231924.png)

- 进入 MySQL 容器，配置 UTF-8 编码

    ```sh
    # 语法
    docker exec -it 容器名 | 容器id bash
    
    # 实际代码
    docker exec -it mysql bash
    ```

    进入容器后，登录 MySQL

    ```sql
    mysql -uroot -p123456
    ```

    查看数据库的编码是否是 UTF-8

    ```sql
    SHOW VARIABLES LIKE '%char%';
    ```

    如图则代表配置成功：

    ![image-20211211232140907](https://cdn.jsdelivr.net/gh/Kele-Bingtang/static/img/Docker/20211211232142.png)

    国际上的 UTF-8，在 MySQL 中，对标的是 uft8mb4。

- 最后在数据库插入报修系统需要的数据（.sql 文件）

    可以用自己的软件连接服务器的数据库，然后插入数据。

- 暴露 3306 端口（如果是租的服务器，则去对应供应商的网页暴露）

    ```sh
    firewall-cmd --permanent --add-port=3306/tcp
    
    # 重启后生效
    firewall-cmd --reload
    ```

    

### 安装Nginx

下载 Nginx 镜像

```sh
docker pull nginx:latest
```

简单启动 Nginx

```sh
docker run -d --name nginx nginx:latest
```

拷贝 Nginx 的配置文件到宿主机

```sh
docker cp nginx:/etc/nginx/conf.d/ /docker/nginx/conf
docker cp nginx:/usr/share/nginx/html /docker/nginx/html
```

删除之前简单启动的 Nginx

```sh
docker rm -f nginx
```

重新启动完整的 Nginx

```sh
docker run -d \
	--name nginx \
    -p 80:80 \
    -v /docker/nginx/conf:/etc/nginx/conf.d \
	-v /docker/nginx/html:/usr/share/nginx/html \
	--network web --network-alias nginx \
	nginx:latest
```

暴露 80 端口（如果是租的服务器，则去对应供应商的网页暴露）

```sh
firewall-cmd --permanent --add-port=80/tcp

# 重启后生效
firewall-cmd --reload
```

### 部署项目

> 部署后台

下载 JDK 8 镜像（安装包）

```sh
docker pull openjdk:8
```

将 bx.jar 放到 `/opt/bx` 目录下。

并在 `/opt/bx` 目录下创建 Dockerfile，添加如下内容：

```dockerfile
FROM openjdk:8

EXPOSE 8089
WORKDIR /ROOT
ADD bx-2.4.0.jar /ROOT/app.jar
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]
```

执行 Dockerfile

```sh
docker build -t bx .
```

执行该文件后生成 bx 镜像，接着启动该 bx 镜像

```sh
docker run -d --name bx \
    --restart always \
    -p 8089:8089 \
    -v /docker/bx/logs:/ROOT/logs \
    -v /docker/bx/static:/ROOT/static \
    --network web --network-alias bx \
    bx:latest
```

> 部署前台

将前端打包后的文件上传至 `/docker/nginx/html/bx`。

- PC 代码在 `/docker/nginx/html/bxht`
- 手机端代码在 `/docker/nginx/html/bxqt`

然后打开 `/usr/local/nginx/conf/nginx.conf` 

```sh
vim /usr/local/nginx/conf/nginx.conf
```

添加如下内容（在 http 里，或者找到其他的 `location` 位置，将下面内容放在其下方）：

```nginx
location /bxhtxt {
    proxy_pass http://bx:8089/;
}

location /bxqtxt {
    proxy_pass http://bx:8089/;
}
```

然后重启 Nginx 容器

```sh
docker restart nginx
```

最后访问成功就代表可以了。

## 6、部署方式二：服务器

### 安装MySQL

......

### 安装Nginx

### 安装环境

```sh
# 全部安装 GCC、PCRE、zlib、OpenSSL
yum install -y gcc pcre pcre-devel zlib zlib-devel openssl openssl-devel

# 查看安装版本
rpm -qa pcre pcre-devel zlib zlib-devel openssl openssl-devel
```

### 下载Nginx

(1) 进入官网查找需要下载版本的链接地址，然后使用 wget 命令进行下载，这里下载在 `/opt/nginx` 目录下

```sh
# 创建指定目录
mkdir -p /opt/nginx
cd /opt/nginx

wget http://nginx.org/download/nginx-1.20.2.tar.gz
```

(2) 解压缩

```sh
# 进入指定目录
cd /opt/nginx

# 解压
tar -xzf nginx-1.20.2.tar.gz
```

(3) 进入资源文件中，发现 configure 文件，执行该文件

```sh
# 进入解压出的目录
cd nginx-1.20.2/

# 执行 configure 
./configure
```

(4) 编译

```sh
make
```

(5) 安装

```sh
make install
```

（6）查看 Nginx 的安装位置

```sh
whereis nignx

cd /usr/local/nginx/sbin
```

![image-20211126145340927](https://cdn.staticaly.com/gh/Kele-Bingtang/static@master/img/Nginx/20211126145348.png)

### 开机自启Nginx

```sh
vim /usr/lib/systemd/system/nginx.service
```

文件添加如下内容：

```sh
[Unit]
Description=nginx web service
Documentation=http://nginx.org/en/docs/
After=network.target

[Service]
Type=forking
PIDFile=/usr/local/nginx/logs/nginx.pid
ExecStartPre=/usr/local/nginx/sbin/nginx -t -c /usr/local/nginx/conf/nginx.conf
ExecStart=/usr/local/nginx/sbin/nginx
ExecReload=/usr/local/nginx/sbin/nginx -s reload
ExecStop=/usr/local/nginx/sbin/nginx -s stop
PrivateTmp=true

[Install]
WantedBy=default.target
```

注意：可执行文件 nginx 根据自己的路径进行修改，以及 .conf 配置文件和 .pid 文件的路径。这份内容是基于默认安装目录的。

填加完成后，如果权限有问题需要进行权限设置，没有则忽略这一步。

```sh
chmod 755 /usr/lib/systemd/system/nginx.service
```

使用系统命令来操作 Nginx 服务（这些命令只是告诉你存在，目前不一定用到）

```sh
# 启动 Nginx
systemctl start nginx

# 停止 Nginx
systemctl stop nginx

# 重启 Nginx
systemctl restart nginx

# 重新加载配置文件
systemctl reload nginx

# 查看 Nginx 状态
systemctl status nginx

# 开机启动
systemctl enable nginx

# 关闭开启启动
systemctl disable nginx
```

### 全局命令配置

方法一：

- 修改 `/etc/profile` 文件

    ```sh
    vim /etc/profile
    
    # 在最后一行添加
    export PATH=$PATH:/usr/local/nginx/sbin
    ```

    可执行文件 nginx 的路径根据自己的路径修改，这里是默认路径。

- 使之立即生效

    ```sh
    source /etc/profile
    ```

- 任意位置执行 nginx 命令，测试成功

    ```sh
    nginx -V
    ```

方法二：

- 将可执行文件 nginx 拷贝一份到 /usr/bin 目录下

    ```sh
    cp /usr/local/nginx/sbin/nginx /usr/bin
    ```

- 任意位置执行 nginx 命令，测试成功

    ```sh
    nginx -V
    ```



访问自己 Linux 的 IP 地址，不需要加端口，如果看到如下图，代表成功

![image-20211126145630109](https://cdn.staticaly.com/gh/Kele-Bingtang/static@master/img/Nginx/20211126145633.png)

### 安装JDK8

下载 JDK8：`https://www.youngkbt.cn/download/dark/轮子库/`。找到 `jdk-8u261-linux-x64.tar.gz`。

- 放到 Linux 的 `/opt` 目录下

- 解压 jdk8 到 `/usr/local/java` 目录下

    ```sh
    # 先创建
    mkdir /usr/local/java
    
    tar -zxvf jdk-8u261-linux-x64.tar.gz -C /usr/local/java  # -C 指定解压的路径
    ```

- 修改 `/etc/profile` 文件

    ```sh
    vim /etc/profile
    
    # 在最后一行添加
    export JAVA_HOME=/usr/local/java/jdk1.8.0_261
    export PATH=$PATH:$JAVA_HOME/bin
    ```

- 使之立即生效

    ```sh
    source /etc/profile
    ```

### 部署项目

进入 jar 包所在的目录下。如：

```sh
cd /opt
```

后台启动 jar 包

```sh
nohup java -jar bx-2.4.0.jar > bx.log 2>&1 &
```

删除启动的 jar 包进程（如果要删除的话）

```sh
# 查看 jar 包进程的信息
netstat -nlp

# 找到 PID/Program name 为 /java 结尾的程序，kill 删除
kill 9308   # 9308 是你的 jar 包进程 ID
```

将前端打包后的文件上传至 `/usr/local/nginx/html`。

- PC 代码在 `/usr/local/nginx/html/bxht`
- 手机端代码在 `/usr/local/nginx/html/bxqt`

然后打开 `/usr/local/nginx/conf/nginx.conf` 

```sh
vim /usr/local/nginx/conf/nginx.conf
```

添加如下内容（在 http 块里，或者找到其他的 `location` 位置，将下面内容放在其下方）：

```nginx
location /bxhtxt {
    proxy_pass http://localhost:8089;
}

location /bxqtxt {
    proxy_pass http://localhost:8089;
}
```

然后重启 Nginx 配置文件

```sh
nginx -s reload
```

最后访问成功就代表可以了。

## 7、mobile端注意事项

- view/receiver/redirect.vue 中把 let mock =true
- 搜索生产需要  改成我们自己的
- 如果要看同学、审核员和接单人不同的页面，要修改views/mobile/redirect.vue中的sf

## 8、PC端注意事项

- 修改vue.config.js
- 搜索生产需要   改成我们自己的

## 9、后端接口注意世想

- 如果要添加日志，注意修改日志路径
- 修改yml的配置文件