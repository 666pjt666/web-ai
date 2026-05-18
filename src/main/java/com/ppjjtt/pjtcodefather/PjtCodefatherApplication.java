package com.ppjjtt.pjtcodefather;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ppjjtt.pjtcodefather.mapper")
public class PjtCodefatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(PjtCodefatherApplication.class, args);
    }

}
