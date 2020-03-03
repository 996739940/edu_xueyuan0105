package cn.eduonline.eduvod.config;

import org.jboss.logging.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 张燕廷
 * @Description swagger配置类
 * @Date 20:17 2020/2/26
 * @Param
 * @return
 */
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
                    .title("网站-讲师管理API文档")
                    .description("本文档描述了讲师管理微服务接口定义")
                    .version("1.0")
                    .contact(new Contact("java", "http://atguigu.com", "aaa@qq.com"))
                    .build();
        }

}
