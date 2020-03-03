package cn.eduonline.eduservice.mapper;

import cn.eduonline.eduservice.entity.EduCourse;
import cn.eduonline.eduservice.entity.coursedto.CourseBaseInfoDto;
import cn.eduonline.eduservice.entity.coursedto.CourseInfoDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-02-05
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**根据课程id查询课程的信息（包含课程基本信息，描述，分类，讲师）*/
    CourseInfoDto getAllCourseInfo(String courseId);

    CourseBaseInfoDto getBaseCourseInfo(String id);
}
