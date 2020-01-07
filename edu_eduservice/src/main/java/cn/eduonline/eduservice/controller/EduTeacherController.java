package cn.eduonline.eduservice.controller;


import cn.eduonline.common.R;
import cn.eduonline.eduservice.entity.EduTeacher;
import cn.eduonline.eduservice.entity.query.TeacherQuery;
import cn.eduonline.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-12-31
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //测试环境，查询所有讲师
//    @GetMapping
//    public List<EduTeacher> getAllTeacher() {
//        List<EduTeacher> list = eduTeacherService.list(null);
//        return list;
//    }

    @GetMapping
    public R getAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    //逻辑删除讲师的方法
    @DeleteMapping("{id}")
    public R deleteTeacherId(@PathVariable String id) {
        boolean result = eduTeacherService.removeById(id);
        if(result) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //分页查询讲师的功能
    @GetMapping("getTeacherPage/{page}/{limit}")
    public R getPageTeacher(@PathVariable long page,
                            @PathVariable long limit) {

        //TODO 测试统一异常管理
//        int i = 1/0;

        Page<EduTeacher> pageTeacher  = new Page<>(page,limit);
        eduTeacherService.page(pageTeacher,null);
        //pageTeacher里面获取分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

//        Map<String,Object> map = new HashMap<>();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);

        return R.ok().data("total",total).data("rows",records);
    }

    //多条件查询带分页
    @PostMapping("getTeacherPageCondition/{page}/{limit}")
    public R getTeacherPageCondition(@PathVariable long page,
                                     @PathVariable long limit,
                                     @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用service的方法
        eduTeacherService.getTeacherPage(pageTeacher,teacherQuery);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    //添加讲师方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据id查询讲师
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    //根据id修改讲师
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean result = eduTeacherService.updateById(eduTeacher);
        if(result) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

