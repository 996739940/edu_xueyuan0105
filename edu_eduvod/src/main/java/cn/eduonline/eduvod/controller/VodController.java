package cn.eduonline.eduvod.controller;

import cn.eduonline.common.R;
import cn.eduonline.eduvod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author 张燕廷
 * @Description vod前台接口
 * @Date 20:20 2020/2/26
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/eduvod/vid")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    /**实现上传视频到阿里云*/
    @PostMapping("uploadVid")
    public R uploadVideoAliyun(@RequestParam("file") MultipartFile file) {
        String videoId = vodService.uploadAliyunVideo(file);
        return R.ok().data("vid",videoId);
    }

    /**删除阿里云视频*/
    @DeleteMapping("{videoId}")
    public R removeAliyunVideoId(@PathVariable String videoId) {
        vodService.deleteAliyunVideoById(videoId);
        return R.ok();
    }

    /**删除多个视频的方法*/
    @DeleteMapping("removeMoreVideo")
    public R deleteMoreVideo(@RequestParam("videoList") List videoList) {
        vodService.removeVideoMore(videoList);
        return R.ok();
    }

    //根据视频id获取视频播放凭证
    @GetMapping("getPlayAuth/{vid}")
    public R getVideoPlayAuth(@PathVariable String vid) {
        String playAuth = vodService.getPlayAuthVideo(vid);
        return R.ok().data("playAuth",playAuth);
    }
}
