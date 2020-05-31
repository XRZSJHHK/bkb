package com.example.demo.service;

import com.example.demo.dao.ClickDao;
import com.example.demo.entity.MajorClick;
import com.example.demo.entity.SchoolClick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClickServiceImpl implements ClickService {
    @Autowired
    ClickDao clickDao;
    @Override
    public void schoolClick(String schoolName){
        if(clickDao.getSchoolClick(schoolName)==null){
            clickDao.addSchoolClick(schoolName);
        }else{
            clickDao.updateSchoolClick(schoolName);
        }
    }
    @Override
    public void majorClick(String majorName){
        if(clickDao.getMajorClick(majorName)==null){
            clickDao.addMajorClick(majorName);
        }else{
            clickDao.updateMajorClick(majorName);
        }
    }
    @Override
    public List<SchoolClick> getSchoolClickAll(){
        return clickDao.getSchoolClickAll();
    }
    @Override
    public List<MajorClick> getMajorClickAll(){
        return clickDao.getMajorClickAll();
    }
}
