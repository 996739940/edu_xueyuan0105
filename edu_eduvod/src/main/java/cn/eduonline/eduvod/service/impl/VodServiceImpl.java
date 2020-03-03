package cn.eduonline.eduvod.service.impl;

import cn.eduonline.eduvod.service.VodService;
import cn.eduonline.eduvod.utils.AliyunVodUtils;
import cn.eduonline.eduvod.utils.ConstantPropertiesUtil;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.reflect.annotation.ExceptionProxy;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张燕廷
 * @Description vod服务层实现类
 * @Date 20:20 2020/2/26
 * @Param
 * @return
 **/
@Service
public class VodServiceImpl implements VodService {

    /**上传视频*/
    @Override
    public String uploadAliyunVideo(MultipartFile file) {
        try {
            //aadgc.avi
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream in = file.getInputStream();
            UploadStreamRequest request =
                    new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                            title, fileName, in);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**删除阿里云视频*/
    @Override
    public void deleteAliyunVideoById(String videoId) {
        try {
            //初始化对象
            DefaultAcsClient client = AliyunVodUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //把要删除视频id设置到request对象里面
            request.setVideoIds(videoId);
            //调用方法实现删除
            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch(Exception e) {

        }
    }

    /**根据多个视频id删除多个视频*/
    @Override
    public void removeVideoMore(List<String> videoList) {
        try {
            //初始化对象
            DefaultAcsClient client = AliyunVodUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //把要删除视频id设置到request对象里面
            //最多支持删除20个 1,2,3
            String vids = StringUtils.join(videoList.toArray(), ",");

            request.setVideoIds(vids);
            //调用方法实现删除
            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");
        }catch(Exception e) {

        }
    }

    /**根据视频id获取视频播放凭证*/
    @Override
    public String getPlayAuthVideo(String vid) {
        try {
            //初始化操作
            DefaultAcsClient client = AliyunVodUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建request对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(vid);

            //调用方法返回response对象
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            //通过response对象获取播放凭证
            String playAuth = response.getPlayAuth();
            return playAuth;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] arg) {
        //把list集合变成 1,2,3
        List<String> list = new ArrayList<>();
        list.add("11");
        list.add("12");
        list.add("13");

        String join = StringUtils.join(list.toArray(), ",");
        System.out.println(join);

    }
}
