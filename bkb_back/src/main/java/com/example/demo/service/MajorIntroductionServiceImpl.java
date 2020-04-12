package com.example.demo.service;

import com.example.demo.dao.MajorIntroductionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorIntroductionServiceImpl implements MajorIntroductionService {
    @Autowired
    MajorIntroductionDao majorIntroductionDao;
    @Override
    public String getMajorIntroductionService(String majorId) {
        return majorIntroductionDao.getMajorIntroductionDao(majorId);
    }
}
