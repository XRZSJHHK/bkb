package com.example.demo.dao;

import com.example.demo.entity.SchoolComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolCommentDao {
    List<SchoolComment> getSchoolCommentDao(String schoolId);
}
