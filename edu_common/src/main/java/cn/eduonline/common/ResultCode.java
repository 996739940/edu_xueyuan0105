package cn.eduonline.common;

/**
 * @ClassName ResultCode
 * @Description 统一前台返回数据的格式实体类
 * @Author 张燕廷
 * @Date 2020/1/9 9:34
 * @Version 1.0
 **/
public interface ResultCode {

    /**成功*/
    int OK = 20000;
     /**失败*/
    int ERROR = 20001;
     /**用户名或密码错误*/
    int LOGIN_ERROR = 20002;
     /**权限不足*/
    int ACCESS_ERROR = 20003;
     /**远程调用失败*/
    int REMOTE_ERROR = 20004;
     /**重复操作*/
    int REPEAT_ERROR = 20005;

}
