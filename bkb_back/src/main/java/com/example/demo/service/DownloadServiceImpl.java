package com.example.demo.service;

import com.example.demo.dao.DownloadDao;
import com.example.demo.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    DownloadDao downloadDao;
    @Override
    public List<Resource> download() {
        return downloadDao.download();
    }
    @Override
    public List<Resource> getPart(String content) {
        List<Resource> all = downloadDao.download();
        List<Resource> part = new ArrayList<>();
        Iterator<Resource> iterator=all.iterator();
        while(iterator.hasNext()){
            Resource r = iterator.next();
            String s =r.getResourceName();
            Pattern pattern = Pattern.compile(content);
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                part.add(r);
            }
        }
        return part;
    }
}
