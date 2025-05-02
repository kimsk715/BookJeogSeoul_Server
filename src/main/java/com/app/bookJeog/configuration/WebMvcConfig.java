package com.app.bookJeog.configuration;

import com.app.bookJeog.interceptor.AlarmInterceptor;
import com.app.bookJeog.interceptor.TestInterceptor;
import com.app.bookJeog.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {


    private final AlarmInterceptor alarmInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(alarmInterceptor).
                addPathPatterns("/post/**", "/main/**");
    }
}
