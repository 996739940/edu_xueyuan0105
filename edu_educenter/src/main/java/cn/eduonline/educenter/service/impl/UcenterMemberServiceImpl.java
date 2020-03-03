package cn.eduonline.educenter.service.impl;

import cn.eduonline.educenter.entity.UcenterMember;
import cn.eduonline.educenter.mapper.UcenterMemberMapper;
import cn.eduonline.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    /**统计某一天注册人数*/
    @Override
    public Integer countNumRegister(String day) {
        return baseMapper.countRegister(day);
    }

    /**openid判断*/
    @Override
    public UcenterMember getOpenId(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        return baseMapper.selectOne(wrapper);
    }

    /**添加扫描人信息到用户表*/
    @Override
    public void addUser(UcenterMember member) {
        baseMapper.insert(member);
    }
}
