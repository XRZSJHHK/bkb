package com.example.demo.service;

import com.example.demo.entity.Resource;

import java.util.List;

public interface DownloadService {
    List<Resource> download();
    List<Resource> getPart(String content);
}
