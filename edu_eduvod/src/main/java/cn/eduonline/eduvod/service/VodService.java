package cn.eduonline.eduvod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author 张燕廷
 * @Description vod服务层接口
 * @Date 20:20 2020/2/26
 * @Param
 * @return
 **/
public interface VodService {
    /**上传视频*/
    String uploadAliyunVideo(MultipartFile file);

    /**删除阿里云视频*/
    void deleteAliyunVideoById(String videoId);

    /**删除多个视频*/
    void removeVideoMore(List<String> videoList);

    String getPlayAuthVideo(String vid);
}
