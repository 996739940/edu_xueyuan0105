package cn.eduonline.eduservice.service.impl;

import cn.eduonline.eduservice.entity.EduCourse;
import cn.eduonline.eduservice.entity.EduCourseDescription;
import cn.eduonline.eduservice.entity.coursedto.CourseInfoDto;
import cn.eduonline.eduservice.entity.form.CourseInfoForm;
import cn.eduonline.eduservice.handler.EduException;
import cn.eduonline.eduservice.mapper.EduCourseMapper;
import cn.eduonline.eduservice.service.EduChapterService;
import cn.eduonline.eduservice.service.EduCourseDescriptionService;
import cn.eduonline.eduservice.service.EduCourseService;
import cn.eduonline.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 张燕廷
 * @since 2020-02-05
 */

@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //向两张表添加数据：课程基本信息表和课程描述表
        //1 添加课程基本信息
        //courseInfoForm转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.insert(eduCourse);
        if(result<=0) {//添加失败
            throw new EduException(20001,"添加课程信息失败");
        }

        String courseId = eduCourse.getId();
        //2 如果添加课程信息成功，再添加课程描述信息
        String description = courseInfoForm.getDescription();
        if(!StringUtils.isEmpty(description)) {
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setId(courseId);//设置课程id到描述对象里面
            eduCourseDescription.setDescription(courseInfoForm.getDescription());
            boolean save = eduCourseDescriptionService.save(eduCourseDescription);
            return courseId;
        }
        return courseId;
    }

    //根据课程id查询课程信息
    @Override
    public CourseInfoForm getCourseInfoFormById(String courseId) {
        //1 查询课程表，得到课程基本信息
        EduCourse eduCourse = baseMapper.selectById(courseId);
        //把eduCourse对象值复制到CourseInfoForm里面
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);

        //2 根据课程id查询描述信息
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        if(eduCourseDescription != null) {
            String description = eduCourseDescription.getDescription();
            courseInfoForm.setDescription(description);
        }
        return courseInfoForm;
    }

    //修改课程信息
    @Override
    public boolean updateCourseInfo(CourseInfoForm courseInfoForm) {
        //1 修改课程基本信息表
        //courseInfoForm转换成eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int result = baseMapper.updateById(eduCourse);
        if(result <= 0) {
            throw new EduException(20001,"修改失败");
        }

        //2 修改课程描述表
        String description = courseInfoForm.getDescription();
        if(!StringUtils.isEmpty(description)) {
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setDescription(description);
            eduCourseDescription.setId(courseInfoForm.getId());
            boolean update = eduCourseDescriptionService.updateById(eduCourseDescription);
            return update;
        }
        return true;
    }

    //根据课程id查询课程的信息（包含课程基本信息，描述，分类，讲师）
    @Override
    public CourseInfoDto getCourseInfoId(String courseId) {
        //调用mapper里面的方法
        return baseMapper.getAllCourseInfo(courseId);
    }

    //发布课程
    @Override
    public boolean publishCourseStatus(String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        int result = baseMapper.updateById(eduCourse);
        return result>0;
    }

    @Override
    public boolean removeCourseInChapterVideo(String courseId) {
        //1 删除课程id删除小节和视频
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程
        int result = baseMapper.deleteById(courseId);

        return true;
    }
}
