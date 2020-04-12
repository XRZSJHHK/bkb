package com.example.demo.controller;

import com.example.demo.entity.Major;
import com.example.demo.service.SchoolMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolMajorController {
    @Autowired
    SchoolMajorService schoolMajorService;
    @RequestMapping(value="/schoolMajor",method = RequestMethod.GET)
    public List<Major> getSchoolMajorController(String schoolId){
        return schoolMajorService.getSchoolMajorService(schoolId);
    }
}
