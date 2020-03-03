package cn.eduonline.eduservice.client;

import cn.eduonline.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author 张燕廷
 * @Description 远程调用vod接口的方法声明
 * @Date 20:18 2020/2/26
 * @Param
 * @return
 **/
@FeignClient("edu-eduvod")
@Component
public interface VodClient {

    /**删除阿里云视频通过视频id*/
    @DeleteMapping(value="/eduvod/vid/{videoId}")
    public R removeAliyunVideoId(@PathVariable("videoId") String videoId);

    /**删除很多阿里云视频通过ids*/
    @DeleteMapping(value="/eduvod/vid/removeMoreVideo")
    public R deleteMoreVideo(@RequestParam("videoList") List<String> videoList);
}
