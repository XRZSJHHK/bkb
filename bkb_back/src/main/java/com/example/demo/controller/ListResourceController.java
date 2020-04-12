package com.example.demo.controller;

import com.example.demo.entity.Resource;
import com.example.demo.service.ListResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListResourceController {
    @Autowired
    ListResourceService listResourceService;
    @RequestMapping(value="/listResource",method = RequestMethod.GET)
    public List<Resource> getListResourceController(){
        return listResourceService.getListResourceService();
    }
}
