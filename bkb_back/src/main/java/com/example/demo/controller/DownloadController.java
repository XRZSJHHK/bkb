package com.example.demo.controller;

import com.example.demo.entity.Resource;
import com.example.demo.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/download")
public class DownloadController {
    @Autowired
    DownloadService downloadService;
    @RequestMapping(value="/all",method = RequestMethod.GET)
    public List<Resource> download(){
        return downloadService.download();
    }
    @RequestMapping(value="/part",method = RequestMethod.GET)
    public List<Resource> getPart(String content){
        return downloadService.getPart(content);
    }
}
