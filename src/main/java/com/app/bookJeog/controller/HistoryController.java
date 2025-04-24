package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.HistoryDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.service.HistoryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/history/*")
public class HistoryController {
    private final HistoryService historyService;
    private final HttpSession session;

    // 조회한 책의 오늘 데이터가 있는지 검사 후 없으면 저장
    @PostMapping("save")
    @ResponseBody
    public void saveHistoryIfNotExists(@RequestBody HistoryDTO historyDTO) {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            // 로그인이 안 된 경우 아무 동작도 하지 않고 리턴
            return;
        }

        historyDTO.setMemberId(member.getId()); // 세션에서 memberId 추가 주입
        historyService.saveHistoryIfNotExists(historyDTO); // DTO 통째로 넘김

    }
}
