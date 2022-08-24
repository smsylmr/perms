package com.example.perms;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.perms.web.mapper")
public class BaseApplication {
    public static void main(String[] args) {

        SpringApplication.run(BaseApplication.class);
    }
}
