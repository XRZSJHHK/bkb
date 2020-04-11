package com.example.demo.dao;

import com.example.demo.entity.School;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchSchoolDao {
    School searchDao(String schoolName);
}



