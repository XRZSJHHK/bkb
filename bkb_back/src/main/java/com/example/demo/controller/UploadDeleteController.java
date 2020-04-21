package com.example.demo.controller;

import com.example.demo.service.UploadDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/resource")
public class UploadDeleteController {
    @Autowired
    UploadDeleteService uploadDeleteService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public int upload(@RequestParam("file") MultipartFile file, @RequestHeader("token") String token) {
        if (file.isEmpty()) {
            return -1;
        }
        return uploadDeleteService.upload(file, token);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public int delete(@RequestParam("resourceName") String resourceName, @RequestHeader("token") String token) {
        return uploadDeleteService.delete(resourceName, token);
    }
}
