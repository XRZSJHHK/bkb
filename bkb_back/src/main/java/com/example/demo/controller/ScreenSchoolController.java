package com.example.demo.controller;

import com.example.demo.entity.School;
import com.example.demo.service.ScreenSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreenSchoolController {
    @Autowired
    ScreenSchoolService screenSchoolService;
    @RequestMapping(value="/screenSchool",method = RequestMethod.GET)
    public School screenController(String labelTwo, String labelNine, String labelSelf){
        return screenSchoolService.screenService(labelTwo,labelNine,labelSelf);
    }
}
