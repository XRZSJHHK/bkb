package com.example.demo.service;

import com.example.demo.dao.SchoolIntroductionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolIntroductionServiceImpl implements SchoolIntroductionService {
    @Autowired
    SchoolIntroductionDao schoolIntroductionDao;
    @Override
    public String getSchoolIntroductionService(String schoolId){
        return schoolIntroductionDao.getSchoolIntroductionDao(schoolId);
    }
}
