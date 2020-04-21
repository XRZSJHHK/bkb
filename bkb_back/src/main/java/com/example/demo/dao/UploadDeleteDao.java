package com.example.demo.dao;

import com.example.demo.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDeleteDao {
    String checkUserIdentity(String token);
    Resource checkResourceExist(String resourceName);
    int upload(String resourceName,double resourceSize,String resourceUrl,String resourceUpdateTime);
    int delete(String resourceName);
}
