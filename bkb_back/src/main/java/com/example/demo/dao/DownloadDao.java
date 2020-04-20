package com.example.demo.dao;

import com.example.demo.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadDao {
    List<Resource> download();
}
