package com.app.bookJeog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class InquiryController {

    // 고객센터(문의)
    @GetMapping("/inquiry")
    public String goToInquiryForm() {
        return "main/inquiry";
    }
}
