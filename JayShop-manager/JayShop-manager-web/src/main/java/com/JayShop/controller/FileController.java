package com.JayShop.controller;

import com.JayShop.common.utils.JsonUtils;
import com.JayShop.service.PictureService;
import com.JayShop.service.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/26.
 */
@Controller
public class FileController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        Map<String,Object> map = pictureService.uploadFile(uploadFile);
        String json = JsonUtils.objectToJson(map);
        return json;
    }

}
