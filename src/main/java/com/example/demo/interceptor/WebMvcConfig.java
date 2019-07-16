package com.example.demo.interceptor;

import com.example.demo.utils.commUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import java.net.URISyntaxException;

@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(baseInterceptor).addPathPatterns("/**").excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png ","**/ueditor/*","/upload/image/*", "/**/*.jpg","/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //本地
        //registry.addResourceHandler("/edit/**").addResourceLocations("file:"+ commUtils.getUplodFilePath()+"/admin/ueditor/jsp/");

        //部署
        String CentOSparh= commUtils.getCentOSPath();
        registry.addResourceHandler("/edit/**").addResourceLocations("file:"+CentOSparh+ "/ueditor/jsp/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+CentOSparh +"/upload/");

        super.addResourceHandlers(registry);
    }
}
