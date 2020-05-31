package com.example.demo.controller;

import com.example.demo.entity.MajorClick;
import com.example.demo.entity.SchoolClick;
import com.example.demo.service.ClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/click")
public class ClickController {
    @Autowired
    ClickService clickService;

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public void schoolClick(String schoolName) {
        clickService.schoolClick(schoolName);
    }
    @RequestMapping(value = "/major", method = RequestMethod.GET)
    public void majorClick(String majorName) {
        clickService.majorClick(majorName);
    }

    @RequestMapping(value="/schoolAll",method = RequestMethod.GET)
    public List<SchoolClick> getSchoolClickAll(){
        return clickService.getSchoolClickAll();
    }
    @RequestMapping(value="/majorAll",method = RequestMethod.GET)
    public List<MajorClick> getMajorClickAll(){
        return clickService.getMajorClickAll();
    }
}
