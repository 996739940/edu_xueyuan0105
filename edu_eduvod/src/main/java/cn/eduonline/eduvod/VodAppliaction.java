package cn.eduonline.eduvod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author 张燕廷
 * @Description vod启动类
 * @Date 20:17 2020/2/26
 * @Param
 * @return
 */
@SpringBootApplication
@EnableEurekaClient
public class VodAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(VodAppliaction.class, args);
    }

}
