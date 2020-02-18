package cn.eduonline.eduservice.controller;


import cn.eduonline.common.R;
import cn.eduonline.eduservice.entity.EduTeacher;
import cn.eduonline.eduservice.entity.query.TeacherQuery;
import cn.eduonline.eduservice.handler.EduException;
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
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     *
     * @Author 张燕廷
     * @Description 前后台联调模拟登录数据
     * @Date 10:04 2020/1/10
     * @Param []
     * @return cn.eduonline.common.R
     **/
    @GetMapping("login")
    public R login() {
        return R.ok().data("token","admin");
    }

    /**
     *
     * @Author 张燕廷
     * @Description 前后台联调模拟登录数据
     * @Date 10:05 2020/1/10
     * @Param []
     * @return cn.eduonline.common.R
     **/
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles","[admin]").data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //测试环境，查询所有讲师
//    @GetMapping
//    public List<EduTeacher> getAllTeacher() {
//        List<EduTeacher> list = eduTeacherService.list(null);
//        return list;
//    }

    @GetMapping("listTeachers")
    public R getAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    /**
     * 逻辑删除讲师的方法
     */
    @DeleteMapping("{id}")
    public R deleteTeacherId(@PathVariable String id) {
        boolean result = eduTeacherService.removeTeacherById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    /**
     * 分页查询讲师的功能
     */
    @PostMapping("getTeacherPage/{page}/{limit}")
    public R getPageTeacher(@PathVariable long page,
                            @PathVariable long limit) {

        //TODO 测试统一异常管理
//        try {
//            int i = 1/0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new EduException(20001,"执行了自定义异常");
//        }

        Page<EduTeacher> pageTeacher  = new Page<>(page,limit);
        eduTeacherService.page(pageTeacher,null);
        //pageTeacher里面获取分页数据
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 多条件查询带分页
     */
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

    /**
     * 添加讲师方法
     * @param eduTeacher
     * @return
     */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 据id查询讲师
     * @param id
     * @return
     */
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    /**
     * 根据id修改讲师
     * @param eduTeacher
     * @return
     */
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

