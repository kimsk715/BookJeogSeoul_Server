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
public class AlarmInterceptor implements HandlerInterceptor {
    private final AlarmService alarmService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null && modelAndView != null) {
            Object member = session.getAttribute("member");
            Object sponsorMember = session.getAttribute("sponsorMember");

            // userType 설정
            if (sponsorMember != null) {
                modelAndView.addObject("userType", "sponsorMember");
            } else if (member != null) {
                modelAndView.addObject("userType", "member");
            }

            // 알람 개수 설정 (member에 대해서만)
            if (member != null) {
                PersonalMemberDTO personalMemberDTO = (PersonalMemberDTO) member;

                int unreadCount = alarmService.unreadAlarmCount(personalMemberDTO.getId());
                log.info("unread count: {}", unreadCount);

                modelAndView.addObject("unreadAlarmCount", unreadCount);
            }
        }
    }

}
