package com.example.demo.service;

import com.example.demo.dao.StuMessageDao;
import com.example.demo.entity.StuMessage;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class StuMessageServiceImpl implements StuMessageService {

    private Logger logger= Logger.getLogger(StuMessageServiceImpl.class);

    @Autowired
    StuMessageDao stuMessageDao;
    @Override
    public List<StuMessage> retrieveAll(){
        return stuMessageDao.retrieveAll();
    }
    @Override
    public StuMessage retrieveStuMessageService(String stuNumber) {
        StuMessage answer=null;
        Jedis jedis = new Jedis("localhost", 6379);   //jedis.auth('');
        Gson gson =new Gson();

        String ans=jedis.get(stuNumber);
        if(ans==null)
        {
            answer=stuMessageDao.retrieveStuMessageDao(stuNumber);
            ans=gson.toJson(answer);
            jedis.setex(stuNumber,60,ans);
        }else{
            answer=gson.fromJson(ans,StuMessage.class);
        }
        logger.debug(gson.toJson(answer));
        logger.error(answer);
        return answer;
    }
    @Override
    public int deleteStuMessageService(String stuNumber){
        return stuMessageDao.deleteStuMessageDao(stuNumber);
    }
    @Override
    public int insertStuMessageService(StuMessage stuMessage){
        if(stuMessageDao.retrieveStuMessageDao(stuMessage.getStuNumber())==null){  //先根据主键查询数据是否已经存在
            return stuMessageDao.insertStuMessageDao(stuMessage);
        }else{
            return 0;
        }
    }
    @Override
    public int updateStuMessageService(StuMessage stuMessage){
        return stuMessageDao.updateStuMessageDao(stuMessage);
    }
}
