package com.example.demo.service;

import com.example.demo.dao.DownloadDao;
import com.example.demo.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    DownloadDao downloadDao;
    @Override
    public List<Resource> download() {
        return downloadDao.download();
    }
}
