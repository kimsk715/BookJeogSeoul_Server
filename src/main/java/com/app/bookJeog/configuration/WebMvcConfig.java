package com.app.bookJeog.configuration;

import com.app.bookJeog.interceptor.AlarmInterceptor;
import com.app.bookJeog.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 어디 인터셉터로 가는지!
        registry.addInterceptor(new TestInterceptor())
                // /admin/으로 시작하는것만 실행해 라는뜻
                .addPathPatterns("/admin/**");
                // /test/ 이거 빼고 다 실행해
//                .excludePathPatterns("/test/**","/asdw/wdsw","/test/**","/asdw/wdsw");
        registry.addInterceptor(new AlarmInterceptor()).
                addPathPatterns("/post-conmment");
    }
}
