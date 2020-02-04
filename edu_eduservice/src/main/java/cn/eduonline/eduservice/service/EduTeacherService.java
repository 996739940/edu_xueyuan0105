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

    boolean removeTeacherById(String id);
}
