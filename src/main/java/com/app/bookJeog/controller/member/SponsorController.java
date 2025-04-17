package com.app.bookJeog.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SponsorController implements MemberControllerDocs {


    // 단체 로그인
    @GetMapping("login/sponsorship")
    public String goToSponsorship() {
        return "login/sponsorship";
    }



    // 단체 비밀번호 찾기
    @GetMapping("login/findpasswd-sponsor")
    public String goToFindPasswdSponsor() {
        return "login/findpasswd-sponsor";
    }



}


