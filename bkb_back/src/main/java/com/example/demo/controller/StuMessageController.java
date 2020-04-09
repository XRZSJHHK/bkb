package com.example.demo.controller;

import com.example.demo.entity.StuMessage;
import com.example.demo.service.StuMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StuMessageController {
    @Autowired
    StuMessageService stuMessageService;
    @RequestMapping(value="/retrieve",method= RequestMethod.GET)
    public StuMessage retrieveStuMessageController(String stuNumber){
        return stuMessageService.retrieveStuMessageService(stuNumber);
    }
    @RequestMapping(value="/retrieve/all",method=RequestMethod.GET)
    public List<StuMessage> retrieveAll(){
        return stuMessageService.retrieveAll();
    }
    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public int deleteStuMessageController(String stuNumber){
        return stuMessageService.deleteStuMessageService(stuNumber);
    }
    @RequestMapping(value="/insert",method=RequestMethod.GET)
    public int insertStuMessageController(String stuNumber,String stuName,String stuSex){
        System.out.println("insert");
        StuMessage stuMessage=new StuMessage();
        stuMessage.setStuNumber(stuNumber);
        stuMessage.setStuName(stuName);
        stuMessage.setStuSex(stuSex);
        return stuMessageService.insertStuMessageService(stuMessage);
    }
    @RequestMapping(value="/update",method=RequestMethod.GET)
    public int updateStuMessageController(String stuNumber,String stuName,String stuSex){
        StuMessage stuMessage=new StuMessage();
        stuMessage.setStuNumber(stuNumber);
        stuMessage.setStuName(stuName);
        stuMessage.setStuSex(stuSex);
        return stuMessageService.updateStuMessageService(stuMessage);
    }
}
