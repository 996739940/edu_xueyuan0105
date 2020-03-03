package cn.eduonline.eduservice.entity.coursedto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CoursePublishVo
 * @Description 课程提交vo对象
 * @Author 张燕廷
 * @Date 2020/2/15 15:58
 * @Version 1.0
 **/
@Data
public class CourseInfoDto implements Serializable {

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    /**只用于显示*/
    private String price;

}
