package com.atguigu.yygh.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// 主启动类 在主启动类所在包，或者子包写服务
// 如果没有添加扫描类,那么扫描不到被依赖的包里面的类;只有添加上扫描类才能扫描到被依赖包里面的东西,还有本包里面的东西


@SpringBootApplication
@ComponentScan(value = "com.atguigu.yygh")              // 扫描被依赖的这个目录下的包,以及本包目录下的这个包
@MapperScan(value = "com.atguigu.yygh.hosp.mapper")     // 因为mapper上面没有注解 所以写这个东西
public class ServiceHospMainStarter {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospMainStarter.class,args);
    }
}
