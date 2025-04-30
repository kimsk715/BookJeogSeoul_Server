package com.app.bookJeog.configuration;


import com.app.bookJeog.interceptor.ImageInterceptor;
import com.app.bookJeog.interceptor.AlarmInterceptor;
import com.app.bookJeog.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 어디 인터셉터로 가는지!
        registry.addInterceptor(new TestInterceptor())
                // /admin/으로 시작하는것만 실행해 라는뜻
                .addPathPatterns("/admin/**");

        registry.addInterceptor(new ImageInterceptor()) // 인터셉터 클래스 등록
                .addPathPatterns("/image/**");
        registry.addInterceptor(new AlarmInterceptor()).
                addPathPatterns("/post-conmment");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///c:/upload/");
    }
}
