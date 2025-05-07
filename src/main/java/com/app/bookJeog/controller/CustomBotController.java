package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.OpenAPIResult;

import com.app.bookJeog.service.CustomBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bot/*")
public class CustomBotController {

    @Autowired
    private CustomBotService customBotService;

    @GetMapping("chat")
    public List<OpenAPIResult> chat(@RequestParam Long isbn){
        return customBotService.getDiscussionTopics(isbn);
    }

//    http://localhost:10000/bot/chat?isbn=9788936433598 출력 예시
}