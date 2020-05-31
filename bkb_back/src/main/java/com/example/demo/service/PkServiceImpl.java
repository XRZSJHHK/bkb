package com.example.demo.service;

import com.example.demo.dao.PkDao;
import com.example.demo.entity.Pk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PkServiceImpl implements PkService {
    @Autowired
    PkDao pkDao;
    @Override
    public List<Pk> getResult(String majorName) {
        return pkDao.getResult(majorName);
    }
}
