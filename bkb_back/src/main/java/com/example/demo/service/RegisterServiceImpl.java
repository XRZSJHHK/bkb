package com.example.demo.service;

import com.example.demo.dao.RegisterDao;
import com.example.demo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    RegisterDao registerDao;
    @Override
    public int insertAccountService(String userName, String userPassword) {
        if(registerDao.testUserNameDao(userName)!=null){
            return -2;//表示用户名已存在
        }else{
            String token=this.getToken();
            while(registerDao.testTokenDao(token)!=null){
                token=this.getToken();
            }
            return registerDao.insertAccountDao(userName,userPassword,"0",token);
        }
    }
    public String getToken(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb=new StringBuffer();
        Random random=new Random();
        for(int i=0;i<16;i++){
            int k=random.nextInt(62);
            sb.append(str.charAt(k));
        }
        return sb.toString();
    }
}
