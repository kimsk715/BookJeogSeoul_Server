package com.app.bookJeog.controller;

import com.app.bookJeog.controller.member.MemberControllerDocs;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.dto.SponsorMemberDTO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.AlarmService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    // 알림 센터
    @GetMapping("/alarm")
    public String goToAlarm(HttpSession session) {
        PersonalMemberDTO foundMember = (PersonalMemberDTO) session.getAttribute("member");
        SponsorMemberDTO sponsorMember = (SponsorMemberDTO) session.getAttribute("sponsor");
        alarmService.readAlarm(foundMember.getId());
        alarmService.readAlarm(sponsorMember.getId());




        return "main/alarm";
    }


}
