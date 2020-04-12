package com.example.demo.dao;

import com.example.demo.entity.Major;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolMajorDao {
    List<Major> getSchoolMajorDao(String schoolId);
}
