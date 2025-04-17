package com.app.bookJeog.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PersonalController implements MemberControllerDocs {


    // 로그인
    @GetMapping("login/login")
    public String goToLogin() {

        return "login/login";
    }


    // 회원가입
    @GetMapping("register/register-member")
    public String goToRegisterMember() {
        return "register/register-member";
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
}


