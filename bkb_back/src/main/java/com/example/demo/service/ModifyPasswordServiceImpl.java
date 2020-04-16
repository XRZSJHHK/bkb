package com.example.demo.service;

import com.example.demo.dao.ModifyPasswordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyPasswordServiceImpl implements ModifyPasswordService {
    @Autowired
    ModifyPasswordDao modifyPasswordDao;
    @Override
    public int updatePasswordService(String token, String newPassword) {
        return modifyPasswordDao.updatePasswordDao(token,newPassword);
    }
}
