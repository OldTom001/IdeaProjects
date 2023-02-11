package com.jeff.jeffhome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jeff.jeffhome.dao")
public class JeffhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeffhomeApplication.class, args);
    }

}
