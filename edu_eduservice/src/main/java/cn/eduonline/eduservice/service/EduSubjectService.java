package cn.eduonline.eduservice.service;

import cn.eduonline.eduservice.entity.EduSubject;
import cn.eduonline.eduservice.entity.subjectdto.SubjectOne;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-02
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importData(MultipartFile file);

    List<SubjectOne> getAllSubjectData();

    //删除分类的方法
    boolean deleteSubjectId(String id);

    //添加一级分类
    boolean saveEduSubject(EduSubject eduSubject);

    //二级分类添加
    boolean saveEduSubjectTwo(EduSubject eduSubject);

}
