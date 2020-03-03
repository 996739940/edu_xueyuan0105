package cn.eduonline.eduservice.service;

import cn.eduonline.eduservice.entity.EduCourse;
import cn.eduonline.eduservice.entity.EduTeacher;
import cn.eduonline.eduservice.entity.query.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 张燕廷
 * @since 2019-12-31
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 分页获取教师的数据
     * @param pageParam
     * @param teacherQuery
     */
    void getTeacherPage(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    /**删除讲师通过id*/
    boolean removeTeacherById(String id);

    Map<String, Object> findTeacherFrontPage(Page<EduTeacher> pageTeacher);

    List<EduCourse> getCourseByTeacherId(String id);
}
