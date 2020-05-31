package com.example.demo.service;

import com.example.demo.entity.MajorClick;
import com.example.demo.entity.SchoolClick;

import java.util.List;

public interface ClickService {
    void schoolClick(String schoolName);
    void majorClick(String majorName);
    List<SchoolClick> getSchoolClickAll();
    List<MajorClick> getMajorClickAll();
}
