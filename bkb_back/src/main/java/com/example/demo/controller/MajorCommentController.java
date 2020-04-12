package com.example.demo.controller;

import com.example.demo.entity.MajorComment;
import com.example.demo.service.MajorCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MajorCommentController {
    @Autowired
    MajorCommentService majorCommentService;
    @RequestMapping(value="/majorComment",method = RequestMethod.GET)
    public List<MajorComment> getMajorCommentController(String majorId){
        return majorCommentService.getMajorCommentServic(majorId);
    }
}
