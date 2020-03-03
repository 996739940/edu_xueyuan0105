package cn.eduonline.eduservice.service.impl;

import cn.eduonline.eduservice.client.VodClient;
import cn.eduonline.eduservice.entity.EduVideo;
import cn.eduonline.eduservice.mapper.EduVideoMapper;
import cn.eduonline.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean removeVideoInfo(String xiaojieId) {
        String videoSourceId = baseMapper.selectById(xiaojieId).getVideoSourceId();

        //判断
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeAliyunVideoId(videoSourceId);
        }
        int result = baseMapper.deleteById(xiaojieId);
        return result>0;
    }

    /**根据课程删除里面的所有视频*/
    @Override
    public void removeVideoByCourseId(String courseId) {
        //删除课程里面所有视频
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(queryWrapper);

        ArrayList<String> vids = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                vids.add(videoSourceId);
            }
        }

        if(vids.size() > 0){
            vodClient.deleteMoreVideo(vids);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }

}
