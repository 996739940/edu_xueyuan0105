package com.eduonline.eduoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Description 文件上传
 * @Author 张燕廷
 * @Date 2020/1/16 15:08
 * @Version 1.0
 **/
public interface FileService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
