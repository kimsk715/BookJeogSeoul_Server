package com.app.bookJeog.controller.member;

import com.app.bookJeog.domain.dto.MemberPersonalMemberDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/personal/*")
@RequiredArgsConstructor
public class PersonalController {


    private final MemberServiceImpl memberServiceImpl;

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


    // 로그인
    @GetMapping("login")
    public String goToLogin() {

        return "login/login";
    }


    // 회원가입
    @GetMapping("register-member")
    public String goToRegisterMember(PersonalMemberVO personalMemberVO) {
        return "register/register-member";
    }


    // 이메일 중복검사
    @ResponseBody
    @PostMapping("check-email")
    public Optional<PersonalMemberVO> checkEmail(@RequestParam String memberEmail) {
        return memberServiceImpl.checkEmail(memberEmail);
    }


    // 회원가입 기능
    @PostMapping("register")
    public String registerMember(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        log.info("memberPersonalMemberDTO: {}", memberPersonalMemberDTO);
        memberServiceImpl.insertPersonalMember(memberPersonalMemberDTO);
        return "redirect:/personal/login";
    }


    // 비밀번호 찾기
    @GetMapping("login/findpasswd-member")
    public String goToFindPasswdMember() {
        return "login/findpasswd-member";
    }


    // 비밀번호 찾기 인증페이지
    @GetMapping("login/findpasswd-member-certi")
    public String goToFindPasswdMemberCerti() {
        return "login/findpasswd-member-certi";
    }


    // 비밀번호 재설정
    @GetMapping("login/set-member-passwd")
    public String gotoFindPasswdMemberCerti() {
        return "login/set-member-passwd";
    }

}