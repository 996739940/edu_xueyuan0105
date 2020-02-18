package cn.eduonline.eduservice.controller;


import cn.eduonline.common.R;
import cn.eduonline.eduservice.entity.EduCourse;
import cn.eduonline.eduservice.entity.coursedto.CourseInfoDto;
import cn.eduonline.eduservice.entity.form.CourseInfoForm;
import cn.eduonline.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 张燕廷
 * @since 2020-02-05
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //删除课程的方法
    @DeleteMapping("{courseId}")
    public R deleteCourseId(@PathVariable String courseId) {
        boolean flag = eduCourseService.removeCourseInChapterVideo(courseId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //TODO 查询所有课程，改造成条件查询带分页
    @GetMapping("findAllCourse")
    public R getAllCourseList() {
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("items",list);
    }
    //最终发布课程的方法
    @PostMapping("publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId) {
        boolean flag = eduCourseService.publishCourseStatus(courseId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //根据课程id查询课程的信息（包含课程基本信息，描述，分类，讲师）
    @GetMapping("getCourseAllInfo/{courseId}")
    public R getCourseAllInfo(@PathVariable String courseId) {
        CourseInfoDto courseInfoDto = eduCourseService.getCourseInfoId(courseId);
        return R.ok().data("courseInfo",courseInfoDto);
    }

    //添加课程（包含描述）
    @PostMapping("addCourse")
    public R addCourse(@RequestBody CourseInfoForm courseInfoForm) {
        //返回添加的课程的id
        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId);
    }

    //根据课程id查询课程信息
    @GetMapping("{courseId}")
    public R getCourseInfoId(@PathVariable String courseId) {
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfoFormById(courseId);
        return R.ok().data("course",courseInfoForm);
    }

    //修改课程信息的方法
    @PostMapping("updateCourse")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        boolean flag = eduCourseService.updateCourseInfo(courseInfoForm);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}

