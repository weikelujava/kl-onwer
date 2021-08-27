package com.owner.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserCenterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterTestApplication.class, args);
    }

}
