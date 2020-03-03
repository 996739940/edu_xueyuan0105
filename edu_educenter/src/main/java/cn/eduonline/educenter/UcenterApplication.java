package cn.eduonline.educenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName UcenterApplication
 * @Description 启动类
 * @Author 张燕廷
 * @Date 2020/2/27 13:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
