package com.example.demo.controller;

import com.example.demo.entity.School;
import com.example.demo.service.SearchSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchSchoolController {
    @Autowired
    SearchSchoolService searchSchoolService;
    @RequestMapping(value="/searchSchool",method = RequestMethod.GET)
    public School searchSchoolController(String schoolName){
        return searchSchoolService.searchService(schoolName);
    }
}
