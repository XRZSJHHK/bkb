package com.example.demo.service;

import com.example.demo.dao.MajorCommentDao;
import com.example.demo.entity.MajorComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorCommentServiceImpl implements MajorCommentService {
    @Autowired
    MajorCommentDao majorCommentDao;
    @Override
    public List<MajorComment> getMajorCommentServic(String majorId) {
        return majorCommentDao.getMajorCommentDao(majorId);
    }
}
