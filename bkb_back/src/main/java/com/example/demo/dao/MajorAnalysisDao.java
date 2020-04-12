package com.example.demo.dao;

import com.example.demo.entity.Major;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorAnalysisDao {
    Major getMajorAnalysisDao(String majorId);
}
