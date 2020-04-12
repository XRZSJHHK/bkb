package com.example.demo.controller;

import com.example.demo.service.SchoolIntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolIntroductionController {
    @Autowired
    SchoolIntroductionService schoolIntroductionService;
    @RequestMapping(value="/schoolIntroduction",method = RequestMethod.GET)
    public String getSchoolIntroductionController(String schoolId){
        return schoolIntroductionService.getSchoolIntroductionService(schoolId);
    }
}
