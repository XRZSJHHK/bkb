package com.example.demo.service;

import com.example.demo.dao.ListResourceDao;
import com.example.demo.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListResourceServiceImpl implements ListResourceService {
    @Autowired
    ListResourceDao listResourceDao;
    @Override
    public List<Resource> getListResourceService() {
        return listResourceDao.getListResourceDao();
    }
}
