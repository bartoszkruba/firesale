package com.company.firesale.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(System.getProperty("user.dir") + "/images");
        registry.addResourceHandler("*.*")
                .addResourceLocations("file:src/main/resources/static/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:images/");
    }
}
