package com.example.demo.controller;

import com.example.demo.entity.Major;
import com.example.demo.service.MajorAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MajorAnalysisController {
    @Autowired
    MajorAnalysisService majorAnalysisService;
    @RequestMapping(value="/majorAnalysis",method = RequestMethod.GET)
    public Major getMajorAnalysisController(String majorId){
        return majorAnalysisService.getMajorAnalysisService(majorId);
    }
}
