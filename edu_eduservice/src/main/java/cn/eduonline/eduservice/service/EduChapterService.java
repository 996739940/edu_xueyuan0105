package cn.eduonline.eduservice.service;

import cn.eduonline.eduservice.entity.EduChapter;
import cn.eduonline.eduservice.entity.chaptervideodto.ChapterDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-07
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id返回课程里面所有的章节和小节
    List<ChapterDto> getAllVideoChapter(String courseId);

    //删除章节
    boolean removeChapterId(String chapterId);

    //2 根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
