package cn.eduonline.educenter.controller;

import cn.eduonline.educenter.entity.UcenterMember;
import cn.eduonline.educenter.service.UcenterMemberService;
import cn.eduonline.educenter.utils.ConstantPropertiesUtil;
import cn.eduonline.educenter.utils.HttpClientUtils;
import cn.eduonline.educenter.utils.JwtUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @ClassName WxApiController
 * @Description 微信扫码登录实现
 * @Author 张燕廷
 * @Date 2020/3/3 17:25
 * @Version 1.0
 **/
@Controller //为了跳转不是返回数据
@RequestMapping("/api/ucenter/wx")  //TODO 为了能够多人进行测试，名称必须一样，实际开发中不需要的
@CrossOrigin
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    //回调方法
    //获取微信扫描人的信息
    @GetMapping("callback")
    public String callBack(String code, String state) {
        //1 获取临时票据
        //在回调方法中创建参数，参数名称必须是code
        //code就是临时票据

        //2 拿着临时票据code值，请求固定的地址
        //为了获取两个值：accessToken凭证和openid微信唯一标识
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        //拼接地址里面的参数
        String accessTokenUrl = String.format(baseAccessTokenUrl, ConstantPropertiesUtil.WX_OPEN_APP_ID, ConstantPropertiesUtil.WX_OPEN_APP_SECRET, code);

        //使用httpclient请求拼接之后的地址，获取accessToken凭证和openid微信唯一标识
        //提供httpclient工具类，直接使用就可以了
        String result = null;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
        }catch(Exception e) {
        }
        //从返回result里面获取到accessToken凭证和openid微信唯一标识
        //result是json数据格式，使用json解析工具gson
        Gson gson = new Gson();
        //把json数据转换map集合
        HashMap map = gson.fromJson(result, HashMap.class);
        //从map集合获取数据
        String accessToken = (String)map.get("access_token");
        String openid = (String)map.get("openid");

        //判断：判断用户表里面是否存在相同openid，如果没有相同openid，调用地址获取扫描人信息，添加到数据库中
        UcenterMember member = memberService.getOpenId(openid);
        if(member == null) {//如果没有相同openid，
            //3 拿着获取到accessToken凭证和openid微信唯一标识再去请求地址，获取扫描人信息
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            //拼接参数
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);

            //使用httpclient请求地址
            String userInfo = null;
            try {
                userInfo = HttpClientUtils.get(userInfoUrl);
                System.out.println("userInfo:"+userInfo);
            }catch(Exception e) {
            }

            //从返回json数据中获取扫描人信息
            HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String)userInfoMap.get("nickname");
            String headimgurl = (String)userInfoMap.get("headimgurl");

            member = new UcenterMember();
            member.setNickname(nickname);
            member.setOpenid(openid);
            member.setAvatar(headimgurl);
            //添加到数据库中
            memberService.addUser(member);
        }
        //生成token值
        String token = JwtUtils.geneJsonWebToken(member);
        //
        return "redirect:http://localhost:3000?token="+token;
    }

    //生成登录的二维码
    //重定向到路径里面
    @GetMapping("login")
    public String createWxLogin() {
        //拼接地址后面参数
        // 1 微信开放平台授权baseUrl
        //%s就是？占位符，拼接参数
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //2 获取回调地址
        String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL;

        //3 对获取回调地址进行url编码，为了防止地址里面有特殊符号
        try {
            //encode两个参数：第一个参数编码的字符串，第二个参数编码方式
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");
        }catch(Exception e) {
            e.printStackTrace();
        }

        //4 设置state
        //内网穿透前置域名
        String state = "atonline0105";

        //5 最终参数的拼接
        String wxUrl = String.format(baseUrl, ConstantPropertiesUtil.WX_OPEN_APP_ID, redirectUrl, state);
        System.out.println(wxUrl);
        //6 重定向到拼接之后地址里面
        return "redirect:"+wxUrl;
    }
}
