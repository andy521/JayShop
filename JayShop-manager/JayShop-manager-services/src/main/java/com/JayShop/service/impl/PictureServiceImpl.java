package com.JayShop.service.impl;

import com.JayShop.common.utils.FtpUtil;
import com.JayShop.common.utils.IDUtils;
import com.JayShop.service.PictureService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/26.
 */
@Service
public class PictureServiceImpl implements PictureService {
    private static Logger logger = Logger.getLogger(PictureServiceImpl.class);
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;

    @Value("${FTP_PORT}")
    private Integer FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASEPATH}")
    private String FTP_BASE_PATH;

    @Value("${FTP_URL_ADDRESS}")
    private String FTP_URL_ADDRESS;

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String,Object> map = new HashMap<>();
        try {
            //生成新的文件名
            String oldName = file.getOriginalFilename();
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            String imagePath =  new DateTime().toString("/yyyy/MM/dd");
            boolean flag = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,imagePath, newName, file.getInputStream());
            if(!flag){
               map.put("error",1);
               map.put("message","文件上传失败");
               return  map;
            }
            map.put("error",0);
            map.put("url",FTP_URL_ADDRESS+imagePath+"/"+newName);
        } catch (IOException e) {
            logger.error("error in uploadFile!,{}", e);
            map.put("error",1);
            map.put("message","文件上传失败");
            return  map;
        }
        return map;
    }
}
