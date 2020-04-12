package com.example.demo.controller;

import com.example.demo.service.AddMajorCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddMajorCommentController {
    @Autowired
    AddMajorCommentService addMajorCommentService;
    @RequestMapping(value="/addMajorComment",method = RequestMethod.POST)
    public int insertMajorCommentController(String token, String majorId, String majorCommentMessage, String majorCommentTime){
        return addMajorCommentService.insertSchoolCommentService(token,majorId,majorCommentMessage,majorCommentTime);
    }
}
