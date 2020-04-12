package com.example.demo.controller;

import com.example.demo.entity.SchoolComment;
import com.example.demo.service.SchoolCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolCommentController {
    @Autowired
    SchoolCommentService schoolCommentService;
    @RequestMapping(value="/schoolComment",method = RequestMethod.GET)
    public List<SchoolComment> getSchoolCommentController(String schoolId){
        return schoolCommentService.getSchoolCommentService(schoolId);
    }
}
