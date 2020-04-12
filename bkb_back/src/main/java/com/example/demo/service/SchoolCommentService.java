package com.example.demo.service;

import com.example.demo.entity.SchoolComment;

import java.util.List;

public interface SchoolCommentService {
    List<SchoolComment> getSchoolCommentService(String schoolId);
}
