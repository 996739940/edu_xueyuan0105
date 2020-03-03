package cn.eduonline.educenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

/**
 * @ClassName SwaggerConfig
 * @Description swagger配置类
 * @Author 张燕廷
 * @Date 2020/2/27 14:23
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("网站-生成统计数据API文档")
                .description("本文档描述了生成统计数据微服务接口定义")
                .version("1.0")
                .contact(new Contact("java", "http://atguigu.com", "aaa@qq.com"))
                .build();
    }
}
