package com.example.demo.serviceTest;

import com.example.demo.entity.Resource;
import com.example.demo.service.DownloadServiceImpl;
import com.google.gson.Gson;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class DownloadServiceImplTest {
    @Autowired
    private DownloadServiceImpl downloadService;
    @Test
    public void getPart() {
        List<Resource> list = downloadService.getPart("RUP");
        Iterator<Resource> iterator = list.iterator();
        Gson gson = new Gson();
        while(iterator.hasNext()){
            Resource res = iterator.next();
            String ans = gson.toJson(res);
            System.out.println(ans);
        }
    }
    @Ignore("不执行test方法")
    @Test
    public void test(){
        System.out.println("this is test");
    }
    @Before
    public void init() {
        System.err.println("init");
    }
    @After
    public void destroy(){
        System.err.println("destroy");
    }
    @BeforeClass
    public static void beforeClass(){
        System.err.println("beforeClass");
    }
    @AfterClass
    public static void afterClass(){
        System.err.println("afterClass");
    }

}