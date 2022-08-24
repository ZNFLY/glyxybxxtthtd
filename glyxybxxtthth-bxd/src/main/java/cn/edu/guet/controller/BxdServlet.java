package cn.edu.guet.controller;

import cn.edu.guet.response.ResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Devere19
 * @Date 2022/8/22 17:16
 * @Version 1.0
 */
@RestController
public class BxdServlet {

    @GetMapping("/AdminServlet/test")
    public ResponseData test(){
        System.out.println("测试使用");
        return new ResponseData("2");
    }

}
