
package com.app.bookJeog.interceptor;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.AlarmService;
import com.app.bookJeog.service.AlarmServiceImpl;
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
           PersonalMemberDTO personalMemberDTO = (PersonalMemberDTO) session.getAttribute("member");


                int unreadCount = alarmService.unreadAlarmCount(personalMemberDTO.getId());
                log.info("unread count: {}", unreadCount);

                modelAndView.addObject("unreadAlarmCount", unreadCount);

        }
   }
}
