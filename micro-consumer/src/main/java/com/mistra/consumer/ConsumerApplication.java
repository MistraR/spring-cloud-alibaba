package com.mistra.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: WangRui
 * @Version: 1.0
 * @Time: 2019/8/4 18:30
 * @Description:
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
