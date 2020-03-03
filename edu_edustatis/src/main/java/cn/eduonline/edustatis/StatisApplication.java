package cn.eduonline.edustatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName UcenterApplication
 * @Description 启动类
 * @Author 张燕廷
 * @Date 2020/2/27 13:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class StatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisApplication.class,args);
    }
}
