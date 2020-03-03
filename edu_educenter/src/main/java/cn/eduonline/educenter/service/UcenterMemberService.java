package cn.eduonline.educenter.service;

import cn.eduonline.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**统计某一天注册人数*/
    Integer countNumRegister(String day);

    void addUser(UcenterMember member);

    UcenterMember getOpenId(String openid);
}
