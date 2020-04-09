package com.example.demo.service;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StuMessageServiceImplTest {
    @Autowired
    private StuMessageServiceImpl stuMessageService;
    @Test
    public void main(){
        Gson gson=new Gson();
        String testAnswer=gson.toJson(stuMessageService.retrieveStuMessageService("001"));
        System.out.println("单元测试："+testAnswer);
    }
}