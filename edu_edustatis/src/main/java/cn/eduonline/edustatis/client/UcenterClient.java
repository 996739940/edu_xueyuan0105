package cn.eduonline.edustatis.client;

import cn.eduonline.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName UcenterClient
 * @Description 调用实体类
 * @Author 张燕廷
 * @Date 2020/2/27 14:39
 * @Version 1.0
 **/
@FeignClient("edu-educenter")
@Component
public interface UcenterClient {
    /**调用根据某一天获取注册人数*/
    @GetMapping("/educenter/member/countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable("day") String day);

}
