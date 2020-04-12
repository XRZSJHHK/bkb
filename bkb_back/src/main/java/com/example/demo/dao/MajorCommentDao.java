package com.example.demo.dao;

import com.example.demo.entity.MajorComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorCommentDao {
    List<MajorComment> getMajorCommentDao(String majorId);
}
