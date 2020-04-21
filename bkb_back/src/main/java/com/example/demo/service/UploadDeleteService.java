package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadDeleteService {
    int upload(MultipartFile file,String token);
    int delete(String resourceName,String token);
}
