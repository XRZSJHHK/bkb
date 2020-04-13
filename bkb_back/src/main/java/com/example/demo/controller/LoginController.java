package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Account testAccountController(String userName,String userPassword){
        return loginService.testAccountService(userName,userPassword);
    }
}
