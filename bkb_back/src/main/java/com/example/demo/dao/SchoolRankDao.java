package com.example.demo.dao;

import com.example.demo.entity.Rank;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRankDao {
    List<Rank> getSchoolRankDao();
}
