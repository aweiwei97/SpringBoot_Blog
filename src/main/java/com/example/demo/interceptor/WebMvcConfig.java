package com.example.demo.interceptor;

import com.example.demo.utils.commUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(baseInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/static/**","/admin/login",
                                    "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                                      "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ commUtils.getUplodFilePath()+"upload/");
       // registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
