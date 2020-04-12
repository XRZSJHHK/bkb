package com.example.demo.service;

import com.example.demo.dao.AddMajorCommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddMajorCommentServiceImpl implements AddMajorCommentService {
    @Autowired
    AddMajorCommentDao addMajorCommentDao;
    @Override
    public int insertSchoolCommentService(String token, String majorId, String majorCommentMessage, String majorCommentTime) {
        String userName=addMajorCommentDao.getUserNameDao(token);
        return addMajorCommentDao.insertMajorCommentDao(majorId,userName,majorCommentMessage,majorCommentTime);
    }
}
