package com.example.demo.dao;

import com.example.demo.entity.MajorClick;
import com.example.demo.entity.SchoolClick;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClickDao {
    SchoolClick getSchoolClick(String schoolName);
    List<SchoolClick> getSchoolClickAll();
    int addSchoolClick(String schoolName);
    int updateSchoolClick(String schoolName);

    MajorClick getMajorClick(String majorName);
    List<MajorClick> getMajorClickAll();
    int addMajorClick(String majorName);
    int updateMajorClick(String majorName);
}
