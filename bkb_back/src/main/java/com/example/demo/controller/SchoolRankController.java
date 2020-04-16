package com.example.demo.controller;

import com.example.demo.entity.Rank;
import com.example.demo.service.SchoolRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolRankController {
    @Autowired
    SchoolRankService schoolRankService;
    @RequestMapping(value="/schoolRank",method = RequestMethod.GET)
    public List<Rank> getSchoolRankController(){
        return schoolRankService.getSchoolRankService();
    }
}
