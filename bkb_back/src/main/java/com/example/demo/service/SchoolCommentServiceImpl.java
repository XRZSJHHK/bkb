package com.example.demo.service;

import com.example.demo.dao.SchoolCommentDao;
import com.example.demo.entity.SchoolComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolCommentServiceImpl implements SchoolCommentService {
    @Autowired
    SchoolCommentDao schoolCommentDao;
    @Override
    public List<SchoolComment> getSchoolCommentService(String schoolId) {
        return schoolCommentDao.getSchoolCommentDao(schoolId);
    }
}
