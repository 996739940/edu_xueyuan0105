package cn.eduonline.eduservice.service;

import cn.eduonline.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-07
 */
public interface EduVideoService extends IService<EduVideo> {

    //删除小节，同时删除小节里面视频
    boolean removeVideoInfo(String videoId);

    //1 删除课程id删除小节和视频
    void removeVideoByCourseId(String courseId);
}
