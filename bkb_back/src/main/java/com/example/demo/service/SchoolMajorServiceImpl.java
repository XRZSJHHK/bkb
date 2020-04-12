package com.example.demo.service;

import com.example.demo.dao.SchoolMajorDao;
import com.example.demo.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolMajorServiceImpl implements SchoolMajorService {
    @Autowired
    SchoolMajorDao schoolMajorDao;
    @Override
    public List<Major> getSchoolMajorService(String schoolId) {
        return schoolMajorDao.getSchoolMajorDao(schoolId);
    }
}
