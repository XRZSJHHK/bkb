package com.example.demo.controller;

import com.example.demo.entity.Pk;
import com.example.demo.service.PkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PkController {
    @Autowired
    PkService pkService;
    @RequestMapping(value="/pk",method = RequestMethod.GET)
    public List<Pk> getResult(String majorName){
        return pkService.getResult(majorName);
    }
}
