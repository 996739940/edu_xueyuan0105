package cn.eduonline.eduservice.service;

import cn.eduonline.eduservice.entity.EduCourse;
import cn.eduonline.eduservice.entity.coursedto.CourseBaseInfoDto;
import cn.eduonline.eduservice.entity.coursedto.CourseInfoDto;
import cn.eduonline.eduservice.entity.form.CourseInfoForm;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 张燕廷
 * @since 2020-02-05
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    /**修改课程信息*/
    boolean updateCourseInfo(CourseInfoForm courseInfoForm);

    /**根据课程id查询课程的信息（包含课程基本信息，描述，分类，讲师）*/
    CourseInfoDto getCourseInfoId(String courseId);

    /**发布课程*/
    boolean publishCourseStatus(String courseId);

    /**删除课程（删除章节小节和视频）*/
    boolean removeCourseInChapterVideo(String courseId);

    Map<String, Object> getPageCourse(Page<EduCourse> pageCourse);

    CourseBaseInfoDto getCouseBaseInfo(String id);
}
