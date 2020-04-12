package com.example.demo.service;

import com.example.demo.dao.ScreenSchoolDao;
import com.example.demo.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenSchoolServiceImpl implements ScreenSchoolService {
    @Autowired
    ScreenSchoolDao screenSchoolDao;
    @Override
    public List<School> screenService(String labelTwo, String labelNine, String labelSelf){
        return screenSchoolDao.screenDao(labelTwo,labelNine,labelSelf);
    }
}
