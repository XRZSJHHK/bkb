package com.example.demo.controller;

import com.example.demo.service.AddSchoolCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddSchoolCommentController {
    @Autowired
    AddSchoolCommentService addSchoolCommentService;
    @RequestMapping(value="/addSchoolComment",method = RequestMethod.POST)
    public int insertSchoolCommentController(String token,String schoolId, String schoolCommentMessage, String schoolCommentTime){
        return addSchoolCommentService.insertSchoolCommentService(token,schoolId,schoolCommentMessage,schoolCommentTime);
    }
}
