package com.example.demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface AddSchoolCommentDao {
    String getUserNameDao(String token);
    int insertSchoolCommentDao(String schoolId,String userName,String schoolCommentMessage,String schoolCommentTime);
}
