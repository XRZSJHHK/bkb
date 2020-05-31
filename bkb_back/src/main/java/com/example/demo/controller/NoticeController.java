package com.example.demo.controller;

import com.example.demo.entity.Notice;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    List<Notice> getNotice() {
        return noticeService.getNotice();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    int addNotice(@RequestHeader("token") String token,String title, String time, String content) {
        return noticeService.addNotice(token,title, time, content);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    int deleteNotice(@RequestHeader("token") String token,String id) {
        return noticeService.deleteNotice(token,id);
    }
}
