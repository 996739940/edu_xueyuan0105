package cn.eduonline.edustatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName UcenterConfig
 * @Description 配置类
 * @Author 张燕廷
 * @Date 2020/2/27 13:35
 * @Version 1.0
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("cn.eduonline.edustatis.mapper")
public class UcenterConfig {

}
