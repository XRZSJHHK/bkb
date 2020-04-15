package com.example.demo.dao;

import com.example.demo.entity.School;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenSchoolDao {
    List<School> screenDao(int labelTwo, int labelNine, int labelSelf);
}
