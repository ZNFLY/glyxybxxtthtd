package cn.edu.guet.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Devere19
 * @Date 2022/8/22 16:50
 * @Version 1.0
 */
@Data
public class ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private String info;
    private Object obj;

    public ResponseData(String status, String info, Object obj) {
        this.status = status;
        this.info = info;
        this.obj = obj;
    }

    public ResponseData(String status, String info) {
        this.status = status;
        this.info = info;
    }

    public ResponseData(boolean isSuccess) {
        this.status = isSuccess ? "success" : "false";
    }

    /**
     * 返回错误信息info统一为：
     * 0：未登录授权请登录，
     * 1：程序错误请刷新，
     * 2：无效参数，
     * 3：缺少传递必要参数
     * 其余的info就自己定义
     *
     * @param info
     */
    public ResponseData(String info) {
        this.status = "false";
        this.info = info;
    }

    public ResponseData(Object obj) {
        this.status = "success";
        this.obj = obj;
    }
}