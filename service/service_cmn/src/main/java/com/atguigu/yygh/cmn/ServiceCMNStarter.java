package com.atguigu.yygh.cmn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.atguigu")
@MapperScan(value = "com.atguigu.yygh.cmn.mapper")
public class ServiceCMNStarter {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCMNStarter.class,args);
    }
}
