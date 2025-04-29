package com.app.bookJeog.configuration;

//import com.app.bookJeog.interceptor.AlarmInterceptor;
import com.app.bookJeog.interceptor.ImageInterceptor;
import com.app.bookJeog.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor())
                .addPathPatterns("/admin/**");
        registry.addInterceptor(new ImageInterceptor()) // 인터셉터 클래스 등록
                .addPathPatterns("/images/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///c:/upload/");
    }
}
