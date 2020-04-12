package com.example.demo.dao;

import com.example.demo.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListResourceDao {
    List<Resource> getListResourceDao();
}
