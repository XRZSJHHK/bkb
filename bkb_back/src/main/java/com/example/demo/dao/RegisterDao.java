package com.example.demo.dao;

import com.example.demo.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDao {
    Account testUserNameDao(String userName);
    Account testTokenDao(String token);
    int insertAccountDao(String userName,String userPassword,String userIdentity,String token);
}
