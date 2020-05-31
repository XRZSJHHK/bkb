package com.example.demo.service;

import com.example.demo.dao.NoticeDao;
import com.example.demo.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;
    @Override
    public List<Notice> getNotice() {
        return noticeDao.getNotice();
    }

    @Override
    public int addNotice(String token,String title, String time, String content) {
        if(noticeDao.checkUserIdentity(token).equals("1")){
            return noticeDao.addNotice(title,time,content);
        }else{
            return -2;
        }
    }

    @Override
    public int deleteNotice(String token,String id) {
        if(noticeDao.checkUserIdentity(token).equals("1")){
            return noticeDao.deleteNotice(id);
        }else{
            return -2;
        }
    }
}
