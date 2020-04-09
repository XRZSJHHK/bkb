package com.example.demo.dao;

import com.example.demo.entity.StuMessage;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StuMessageDao {
    List<StuMessage> retrieveAll();
    StuMessage retrieveStuMessageDao(String stuNumber);
    int deleteStuMessageDao(String stuNumber);
    int insertStuMessageDao(StuMessage stuMessage);
    int updateStuMessageDao(StuMessage stuMessage);
}
