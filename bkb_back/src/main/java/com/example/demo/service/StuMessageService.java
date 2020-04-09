package com.example.demo.service;

import com.example.demo.entity.StuMessage;

import java.util.List;

public interface StuMessageService {
    List<StuMessage> retrieveAll();
    StuMessage retrieveStuMessageService(String stuNumber);
    int deleteStuMessageService(String stuNumber);
    int insertStuMessageService(StuMessage stuMessage);
    int updateStuMessageService(StuMessage stuMessage);
}
