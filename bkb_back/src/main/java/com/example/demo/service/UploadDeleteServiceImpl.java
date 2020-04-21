package com.example.demo.service;

import com.example.demo.dao.UploadDeleteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class UploadDeleteServiceImpl implements UploadDeleteService {
    @Autowired
    UploadDeleteDao uploadDeleteDao;
    @Override
    public int upload(MultipartFile file,String token) {
        if(uploadDeleteDao.checkUserIdentity(token).equals("1")){
            String fileName=file.getOriginalFilename(); //文件名
            if(uploadDeleteDao.checkResourceExist(fileName)!=null){
                return -3;
            }
            String nowDate = null; //网络时间
            try {
                URL url = new URL("http://www.taobao.com");
                URLConnection uc = url.openConnection();
                uc.connect();
                Long ld = uc.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
                Date date = new Date(ld);
                nowDate = sdf.format(date);
            } catch (Exception e) {
                e.getMessage();
            }
            double size=file.getSize(); //文件大小，单位字节
            String filePath="C:/Users/19191/Desktop/test/file/";  //本地部署
            //String filePath="/var/lib/tomcat8/webapps/ROOT/bkb_file/";  //云部署
            File dest = new File(filePath + fileName);
            String url="http://49.234.66.166:8080/bkb_file/"+fileName;
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.getMessage();
            }
            return uploadDeleteDao.upload(fileName,size,url,nowDate);
        }else{
            return -2;
        }
    }
    @Override
    public int delete(String resourceName,String token){
        if(uploadDeleteDao.checkUserIdentity(token).equals("1")){
            String filePath="C:/Users/19191/Desktop/test/file/";  //本地部署
            //String filePath="/var/lib/tomcat8/webapps/ROOT/bkb_file/";  //云部署
            File dest = new File(filePath + resourceName);
            if(dest.exists()){
                if(dest.delete()){
                    return uploadDeleteDao.delete(resourceName);
                }
            }
        }
        return -1;
    }
}
