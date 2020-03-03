package cn.eduonline.eduservice.entity.coursedto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CourseBaseInfoDto
 * @Description 前台返回dto对象
 * @Author 张燕廷
 * @Date 2020/3/1 19:58
 * @Version 1.0
 **/
@Data
public class CourseBaseInfoDto implements Serializable {

    /**课程名称*/
    private String title;
    /**封面*/
    private String cover;
    /**课时数*/
    private Integer lessonNum;
    /**一级分类*/
    private String subjectLevelOne;
    /**二级分类*/
    private String subjectLevelTwo;
    /**讲师名称*/
    private String teacherName;
    /**课程价格*/
    private String price;
    /**课程描述*/
    private String description;
    private Integer buyCount;
    private Integer viewCount;
    /**讲师头像*/
    private String avatar;
    /**讲师资历*/
    private String intro;
}
