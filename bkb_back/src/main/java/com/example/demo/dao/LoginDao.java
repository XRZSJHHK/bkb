package com.example.demo.dao;

import com.example.demo.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {
    Account testAccountDao(String userName);
}
