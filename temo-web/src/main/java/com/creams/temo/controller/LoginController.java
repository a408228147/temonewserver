package com.creams.temo.controller;

import com.creams.temo.biz.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("login")
public class LoginController {


    @Autowired
    UserServer userBiz;
    @GetMapping("userlogin")
    public String test()
    {
        return userBiz.queryUserByName("jelly").getUserName();
    }
}
