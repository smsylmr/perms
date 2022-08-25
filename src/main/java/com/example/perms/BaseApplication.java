package com.example.perms;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"com.example.perms.*"})
@MapperScan("com.example.perms.web.mapper")
public class BaseApplication {
    public static void main(String[] args) {

        SpringApplication.run(BaseApplication.class);
    }
}
