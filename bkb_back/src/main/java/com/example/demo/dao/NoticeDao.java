package com.example.demo.dao;

import com.example.demo.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {
    String checkUserIdentity(String token);
    List<Notice> getNotice();
    int addNotice(String title,String time,String content);
    int deleteNotice(String id);
}
