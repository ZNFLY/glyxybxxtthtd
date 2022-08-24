package cn.edu.guet.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"cn.edu.guet.mapper"})
public class MyBatisConfig {
}