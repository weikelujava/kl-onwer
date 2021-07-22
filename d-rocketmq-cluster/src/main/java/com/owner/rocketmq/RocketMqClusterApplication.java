package com.owner.rocketmq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lukewei
 * @date 2021/7/20 13:47
 */
@SpringBootConfiguration
@SpringBootApplication
public class RocketMqClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqClusterApplication.class,args);
    }
}
