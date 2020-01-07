package cn.eduonline.eduservice.service;

import cn.eduonline.eduservice.entity.EduTeacher;
import cn.eduonline.eduservice.entity.query.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-12-31
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void getTeacherPage(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

}
