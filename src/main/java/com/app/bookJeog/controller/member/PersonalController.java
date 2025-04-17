package com.app.bookJeog.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/personal/*")
@RequiredArgsConstructor
public class PersonalController {

    // 개인 마이페이지 조회
    @GetMapping("mypage")
    public String personalMypage() {
        return "member/mypage";
    }

    // 개인 마이페이지 - 내 스크랩(도서 찜)
    @GetMapping("mypage/scrap")
    public String gotoMemberScrap() {
        return "member/scrap-mypage";
    }

    // 개인 마이페이지 - 내 독후감
    @GetMapping("mypage/book-post")
    public String gotoMemberBookPost() {
        return "member/my-post";
    }

    // 개인 마이페이지 - 프로필 수정
    @GetMapping("mypage/profile")
    public String gotoMemberProfile() {
        return "member/my-profile";
    }

    // 개인 마이페이지 - 비밀번호 확인
    @GetMapping("mypage/password-check")
    public String gotoMemberPasswordCheck() {
        return "member/password-check";
    }

    // 개인 마이페이지 - 마일리지 조회
    @GetMapping("mypage/mileage")
    public String gotoMemberMileage() {
        return "member/mileage";
    }

    // 개인 회원탈퇴
    @GetMapping("leave")
    public String gotoMemberLeave() {
        return "member/leave-member";
    }

    // 개인 회원탈퇴 - 탈퇴사유 입력
    @GetMapping("leave/reason")
    public String gotoMemberLeaveReason() {
        return "member/leave-reason";
    }
}
