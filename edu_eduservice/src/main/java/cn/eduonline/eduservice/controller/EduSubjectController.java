package cn.eduonline.eduservice.controller;


import cn.eduonline.common.R;
import cn.eduonline.eduservice.entity.EduSubject;
import cn.eduonline.eduservice.entity.subjectdto.SubjectOne;
import cn.eduonline.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author 张燕廷
 * @Description 课程科目 前端控制器
 * @Date 20:32 2020/2/26
 * @Param
 * @return
 **/
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**1 读取上传的excel里面分类数据，添加到数据库中*/
    @PostMapping("import")
    public R importSubjectData(MultipartFile file) {
        //list集合存储的错误信息
        List<String> msg = eduSubjectService.importData(file);
        if(msg.size() == 0){
            return R.ok().message("批量导入成功");
        }else{
            return R.error().message("部分数据导入失败").data("messageList", msg);
        }
    }

    /**2 返回所有的 分类信息（一级和二级）*/
    @GetMapping("getAllSubject")
    public R getSubjcetAll() {
        List<SubjectOne> list = eduSubjectService.getAllSubjectData();
        return R.ok().data("items",list);
    }

    /**3 分类删除的方法*/
    @DeleteMapping("{id}")
    public R removeSubjectId(@PathVariable String id) {
        boolean flag = eduSubjectService.deleteSubjectId(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**添加二级分类*/
    @PostMapping("addLevelTwo")
    public R addTwoLevel(@RequestBody EduSubject eduSubject) {
        boolean save = eduSubjectService.saveEduSubjectTwo(eduSubject);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**添加一级分类*/
    @PostMapping("addLevelOne")
    public R addOneLevel(@RequestBody EduSubject eduSubject) {
        boolean save = eduSubjectService.saveEduSubject(eduSubject);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

