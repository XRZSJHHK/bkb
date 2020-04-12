package com.example.demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface AddMajorCommentDao {
    String getUserNameDao(String token);
    int insertMajorCommentDao(String majorId,String userName,String majorCommentMessage,String majorCommentTime);
}
