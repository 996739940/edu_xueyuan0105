package cn.eduonline.eduservice.service.impl;

import cn.eduonline.eduservice.entity.EduVideo;
import cn.eduonline.eduservice.mapper.EduVideoMapper;
import cn.eduonline.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Queue;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-07
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    // TODO 删除小节，同时删除小节里面视频
    @Override
    public boolean removeVideoInfo(String videoId) {
        int result = baseMapper.deleteById(videoId);
        return result>0;
    }

    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }

}
