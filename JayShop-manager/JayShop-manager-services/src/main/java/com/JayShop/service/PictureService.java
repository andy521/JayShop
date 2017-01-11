package com.JayShop.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/26.
 */
public interface PictureService {
    Map<String, Object>  uploadFile(MultipartFile file);
}
