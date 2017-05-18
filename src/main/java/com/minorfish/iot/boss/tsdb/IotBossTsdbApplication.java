package com.minorfish.iot.boss.tsdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class IotBossTsdbApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder().web(true).build();
        app.run(IotBossTsdbApplication.class, args);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SessionLoginInterceptor())
//                .addPathPatterns("/web/**")
//                .excludePathPatterns("/web/user/_login*", "/web/user/_logout*");
    }

}
