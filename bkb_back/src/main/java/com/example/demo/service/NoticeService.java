package com.example.demo.service;

import com.example.demo.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getNotice();
    int addNotice(String token,String title,String time,String content);
    int deleteNotice(String token,String id);
}
