package com.example.demo.controller;

import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public int insertAccountController(String userName,String userPassword){
        return registerService.insertAccountService(userName,userPassword);
    }
}
