package com.example.demo.controller;

import com.example.demo.service.MajorIntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MajorIntroductionController {
    @Autowired
    MajorIntroductionService majorIntroductionService;
    @RequestMapping(value="/majorIntroduction",method = RequestMethod.GET)
    public String getMajorIntroductionController(String majorId){
        return majorIntroductionService.getMajorIntroductionService(majorId);
    }
}
