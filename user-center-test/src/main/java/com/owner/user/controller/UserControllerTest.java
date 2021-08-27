package com.owner.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lukewei
 * @date 2021/8/27 9:53
 */
@RestController
public class UserControllerTest {


    @GetMapping("/user/info")
    public String getUserInfo(){
        return "user2";
    }
}
