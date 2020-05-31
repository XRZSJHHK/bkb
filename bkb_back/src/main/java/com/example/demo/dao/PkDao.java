package com.example.demo.dao;

import com.example.demo.entity.Pk;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PkDao {
    List<Pk> getResult(String majorName);
}
