package cn.eduonline.eduvod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Author 张燕廷
 * @Description 阿里云配置类
 * @Date 20:14 2020/2/26
 * @Param
 * @return
 **/
public class AliyunVodUtils {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws Exception {
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
