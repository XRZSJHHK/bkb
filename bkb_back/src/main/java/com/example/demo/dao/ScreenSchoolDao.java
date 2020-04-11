package com.example.demo.dao;

import com.example.demo.entity.School;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenSchoolDao {
    School screenDao(String labelTwo,String labelNine,String labelSelf);
}
