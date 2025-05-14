package com.app.bookJeog.interceptor;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.service.AlarmService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginIntercepter implements HandlerInterceptor {
    private final AlarmService alarmService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if(session == null) {
            response.sendRedirect("/personal/login");
            return false;
        }
        return true;
    }
}
