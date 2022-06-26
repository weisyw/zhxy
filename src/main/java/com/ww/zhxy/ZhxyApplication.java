package com.ww.zhxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ZhxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhxyApplication.class, args);
    }

}
