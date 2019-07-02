package com.example.demo.interceptor;

import com.example.demo.utils.commUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;
import sun.net.www.content.image.png;

import javax.annotation.Resource;

@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(baseInterceptor).addPathPatterns("/**").excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png ", "/**/*.jpg","/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ commUtils.getUplodFilePath()+"upload/");
        super.addResourceHandlers(registry);
       // registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
