package com.example.demo.service;

import com.example.demo.dao.MajorAnalysisDao;
import com.example.demo.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorAnalysisServiceImpl implements MajorAnalysisService {
    @Autowired
    MajorAnalysisDao majorAnalysisDao;
    @Override
    public Major getMajorAnalysisService(String majorId) {
        return majorAnalysisDao.getMajorAnalysisDao(majorId);
    }
}
