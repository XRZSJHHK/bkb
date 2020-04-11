package com.example.demo.service;

import com.example.demo.dao.SearchSchoolDao;
import com.example.demo.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchSchoolServiceImpl implements SearchSchoolService {
    @Autowired
    SearchSchoolDao searchSchoolDao;
    public School searchService(String schoolName){
        return searchSchoolDao.searchDao(schoolName);
    }
}
