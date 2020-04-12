package com.example.demo.service;

import com.example.demo.entity.MajorComment;

import java.util.List;

public interface MajorCommentService {
    List<MajorComment> getMajorCommentServic(String majorId);
}
