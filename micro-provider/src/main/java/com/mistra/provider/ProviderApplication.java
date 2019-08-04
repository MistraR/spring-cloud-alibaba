package com.mistra.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: WangRui
 * @Version: 1.0
 * @Time: 2019/8/4 18:33
 * @Description:
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
