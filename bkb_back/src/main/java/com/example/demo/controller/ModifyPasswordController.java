package com.example.demo.controller;

import com.example.demo.service.ModifyPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModifyPasswordController {
    @Autowired
    ModifyPasswordService modifyPasswordService;
    @RequestMapping(value="modifyPassword",method = RequestMethod.POST)
    public int updatePasswordController(String token,String newPassword){
        return modifyPasswordService.updatePasswordService(token,newPassword);
    }
}
