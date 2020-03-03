package cn.eduonline.eduservice.service.impl;

import cn.eduonline.eduservice.entity.EduChapter;
import cn.eduonline.eduservice.entity.EduVideo;
import cn.eduonline.eduservice.entity.chaptervideodto.ChapterDto;
import cn.eduonline.eduservice.entity.chaptervideodto.VideoDto;
import cn.eduonline.eduservice.handler.EduException;
import cn.eduonline.eduservice.mapper.EduChapterMapper;
import cn.eduonline.eduservice.service.EduChapterService;
import cn.eduonline.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-07
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    /**根据课程id返回课程里面所有的章节和小节*/
    @Override
    public List<ChapterDto> getAllVideoChapter(String courseId) {
        //1 根据课程id查询课程所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapperChapter);

        //2根据课程id查询课程里面所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(wrapperVideo);

        //3 遍历所有的章节，进行数据封装
        //创建list集合，用于存储最终数据
        List<ChapterDto> finalChapterVideoList = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            //得到每个章节
            EduChapter eduChapter = eduChapters.get(i);
            //转换成ChapterDto对象
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(eduChapter,chapterDto);
            //放到list集合里面
            finalChapterVideoList.add(chapterDto);

            //定义list集合用于存储章节里面所有的小节
            List<VideoDto> videoDtoList = new ArrayList<>();
            //4 遍历所有的小节，判断id值，获取章节里面小节，进行封装
            for (int m = 0; m < eduVideos.size(); m++) {
                //获取每个小节
                EduVideo eduVideo = eduVideos.get(m);
                //判断小节里面chapterid 和 章节里面id 是否相同
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //获取小节，转换videoDto对象
                    VideoDto videoDto = new VideoDto();
                    BeanUtils.copyProperties(eduVideo,videoDto);
                    //放到list集合
                    videoDtoList.add(videoDto);
                }
            }
            //把遍历转换之后封装小节list集合放到章节里面
            chapterDto.setChildren(videoDtoList);
        }
        return finalChapterVideoList;
    }

    /**删除章节*/
    @Override
    public boolean removeChapterId(String chapterId) {
        //1 判断章节里面是否有小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        //章节里面有小节
        if(count > 0) {
            //不进行删除
            throw new EduException(20001,"章节里面有小节，不能删除");
        } else {
            //没有小节
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}