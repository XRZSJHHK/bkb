package com.example.demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UploadDao {
    int upload(String resourceName,double resourceSize,String resourceUrl,String resourceUpdateTime);
}
