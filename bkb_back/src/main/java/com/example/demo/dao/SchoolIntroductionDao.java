package com.example.demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface SchoolIntroductionDao {
    String getSchoolIntroductionDao(String schoolId);
}
