package com.example.demo.service;

import com.example.demo.dao.SchoolRankDao;
import com.example.demo.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolRankServiceImpl implements SchoolRankService {
    @Autowired
    SchoolRankDao schoolRankDao;
    @Override
    public List<School> getSchoolRankService() {
        return schoolRankDao.getSchoolRankDao();
    }
}
