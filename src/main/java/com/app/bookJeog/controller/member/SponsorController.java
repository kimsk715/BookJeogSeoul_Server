package com.app.bookJeog.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/sponsor/*")
@RequiredArgsConstructor
public class SponsorController {

    // 단체 마이페이지 조회
    @GetMapping("mypage")
    public String SponsorMypage() {
        return "organization/mypage";
    }

    // 단체 마이페이지 - 내 기부글
    @GetMapping("mypage/donation")
    public String donationMypage() {
        return "organization/my-donation";
    }

    // 단체 마이페이지 - 비밀번호 확인
    @GetMapping("mypage/password-check")
    public String gotoSponsorPasswordCheck() {
        return "organization/password-check";
    }

    // 단체 마이페이지 - 내 기부 인증글
    @GetMapping("mypage/donate-cert")
    public String donateCertMypage() {
        return "organization/my-donate-post";
    }

    // 단체 마이페이지 - 회원정보 변경
    @GetMapping("mypage/profile")
    public String SponsorMypageProfile() {
        return "organization/my-profile";
    }

    // 단체 회원탈퇴
    @GetMapping("leave")
    public String gotoSponsorLeave() {
        return "organization/leave-organization";
    }

    // 단체 회원탈퇴 - 탈퇴사유 입력
    @GetMapping("leave/reason")
    public String gotoSponsorLeaveReason() {
        return "organization/leave-reason";
    }
}
