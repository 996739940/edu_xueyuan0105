package cn.eduonline.eduservice.service.impl;

import cn.eduonline.eduservice.entity.EduCourse;
import cn.eduonline.eduservice.entity.EduTeacher;
import cn.eduonline.eduservice.entity.query.TeacherQuery;
import cn.eduonline.eduservice.mapper.EduTeacherMapper;
import cn.eduonline.eduservice.service.EduCourseService;
import cn.eduonline.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-12-31
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduCourseService eduCourseService;

    @Override
    public void getTeacherPage(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String name = teacherQuery.getName();
        String level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean removeTeacherById(String id) {
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    /**前台系统 讲师列表分页*/
    @Override
    public Map<String, Object> findTeacherFrontPage(Page<EduTeacher> pageTeacher) {
        //调用方法分页查询，把分页所有数据封装到pageTeacher对象里面
        baseMapper.selectPage(pageTeacher,null);
        //从pageTeacher对象获取分页数据封装到map集合
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        //当前页
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        //每页记录数
        long size = pageTeacher.getSize();
        //是否有上一页
        boolean hasPrevious = pageTeacher.hasPrevious();
        //是否有下一页
        boolean hasNext = pageTeacher.hasNext();

        //放到map集合
        Map<String,Object> map = new HashMap<>(256);
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    /**2 根据讲师id查询讲师所讲课程*/
    @Override
    public List<EduCourse> getCourseByTeacherId(String id) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("status","Normal");
        wrapper.eq("teacher_id",id);
        List<EduCourse> list = eduCourseService.list(wrapper);
        return list;
    }

}
