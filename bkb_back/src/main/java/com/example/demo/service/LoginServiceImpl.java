package com.example.demo.service;

import com.example.demo.dao.LoginDao;
import com.example.demo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;
    @Override
    public Account testAccountService(String userName, String userPassword) {
        Account account = loginDao.testAccountDao(userName);
        if(account==null){
            account=new Account();
            account.setUserPassword("0"); //表示用户不存在
        }else{
            if(!userPassword.equals(account.getUserPassword())){
                account.setUserName(null);
                account.setUserPassword("1"); //表示用户密码错误
                account.setUserIdentity(null);
                account.setToken(null);
            }else{
                account.setUserPassword("2"); //表示用户名、用户密码均正确；
            }
        }
        return account;
    }
}
