package cn.eduonline.eduservice.controller;


import cn.eduonline.common.R;
import cn.eduonline.eduservice.entity.EduVideo;
import cn.eduonline.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //1 添加小节
    @PostMapping("saveVideo")
    public R saveVideoInfo(@RequestBody EduVideo eduVideo) {
        boolean save = eduVideoService.save(eduVideo);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //2 根据小节id查询
    @GetMapping("{videoId}")
    public R getVideoInfoId(@PathVariable String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }

    //3 修改小节
    @PostMapping("updateVideo")
    public R updateVideoInfo(@RequestBody EduVideo eduVideo) {
        boolean result = eduVideoService.updateById(eduVideo);
        if(result) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //4 删除小节
    @DeleteMapping("{videoId}")
    public R deleteVideoId(@PathVariable String videoId) {
        boolean flag = eduVideoService.removeVideoInfo(videoId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

