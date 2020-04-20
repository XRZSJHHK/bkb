package com.example.demo.controller;

import com.example.demo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@RestController
public class UploadController {
    @Autowired
    UploadService uploadService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public int upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return -1;
        }
        double size = file.getSize();  //得到文件大小，单位字节
        String fileName = file.getOriginalFilename();  //得到文件名
        String filePath = "C:/Users/19191/Desktop/test/file/";
        //String filePath="/var/lib/tomcat8/webapps/ROOT/bkb_file/"; //云部署的存储路径
        File dest = new File(filePath + fileName);
        String url="http://49.234.66.166:8080/bkb_file/"+fileName;
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.getMessage();
        }
        return uploadService.upload(fileName,size,url);
    }
}
