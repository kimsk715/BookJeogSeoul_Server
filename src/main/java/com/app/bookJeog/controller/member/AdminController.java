package com.app.bookJeog.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    // 어드민 로그인
    @GetMapping("login/admin")
    public String goToAdmin() {
        return "login/admin";
    }
}
