package com.app.bookJeog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

    // 메인으로 이동
    @GetMapping("main/main")
    public String goToMain(Model model) {

        return "main/main";
    }
}
