package com.kc.goodlife;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.servlet.MultipartConfigElement;
/**
 * @author liuyan
 */
@SpringBootApplication
/*
@ComponentScan("com.kc.goodlife.config")
*/
@ComponentScan("com.kc.goodlife.*")
@MapperScan("com.kc.goodlife.mapper")
@EnableWebMvc
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("5MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("5MB");
        return factory.createMultipartConfig();
    }
}
