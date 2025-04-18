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
public class NoticeController  {

        @GetMapping("/notice")
    public String goToNoticeList(){
        return "main/notice_event";
    }
}
