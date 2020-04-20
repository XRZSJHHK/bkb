package com.example.demo.service;

import com.example.demo.dao.UploadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    UploadDao uploadDao;
    @Override
    public int upload(String resourceName, double resourceSize, String resourceUrl) {
        String nowDate = null;
        try {
            //获取网络时间
            URL url = new URL("http://www.taobao.com");//获取资源对象
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect();//建立连接
            Long ld = uc.getDate();//读取网站时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            Date date = new Date(ld);
            nowDate = sdf.format(date);
        } catch (Exception e) {
            e.getMessage();
        }
        return uploadDao.upload(resourceName, resourceSize, resourceUrl, nowDate);
    }
}
