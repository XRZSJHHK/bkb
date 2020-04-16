package com.example.demo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ModifyPasswordDao {
    int updatePasswordDao(String token,String newPassword);
}
