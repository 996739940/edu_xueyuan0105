package cn.eduonline.eduservice.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName EduException
 * @Description 统一异常类
 * @Author 张燕廷
 * @Date 2020/1/9 9:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EduException extends RuntimeException{

    private Integer code;
    private String msg;

}
