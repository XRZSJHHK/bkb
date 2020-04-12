package com.example.demo.service;

import com.example.demo.dao.AddSchoolCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddSchoolCommentServiceImpl implements AddSchoolCommentService {
    @Autowired
    AddSchoolCommentDao addSchoolCommentDao;
    @Override
    public int insertSchoolCommentService(String token,String schoolId, String schoolCommentMessage, String schoolCommentTime) {
        String userName=addSchoolCommentDao.getUserNameDao(token);
        return addSchoolCommentDao.insertSchoolCommentDao(schoolId,userName,schoolCommentMessage,schoolCommentTime);
    }
}
