package cn.edu.guet.util;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author lrt
 * @Date 2020/12/11 17:08
 * @Version 1.0
 * 这里是保存路径配置，也就是上传文件保存的地方
 **/
@Component
public class PathUtil {

    public static String getUploadPath() {
//        开发用上面，生产用下面
//        File path = null;
//        try {
//            path = new File(ResourceUtils.getURL("classpath:").getPath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (!path.exists()) path = new File("");
//        File upload = new File(path.getAbsolutePath(), "static/bxdimg/");
//        if (!upload.exists()) upload.mkdirs();
//        return upload.getAbsolutePath();

        String path = "E:\\bxdimg\\";
        File upload = new File(path);
        if (!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }

    public static void main(String[] args) {
        System.out.println(PathUtil.getUploadPath());
    }
}
