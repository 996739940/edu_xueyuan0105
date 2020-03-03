package cn.eduonline.eduservice.entity.subjectdto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张燕廷
 * @Description 一级分类实体类
 * @Date 20:16 2020/2/26
 * @Param
 * @return
 */
@Data
public class SubjectOne {

    private String id;
    /**一级分类名称*/
    private String title;

    /**一级分类的所有的二级分类*/
    private List<SubjectTwo> children = new ArrayList<>();
}
