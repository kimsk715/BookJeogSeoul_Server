package com.app.bookJeog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/*")
public class PostController {
    // 토론게시판 이동
    @GetMapping("discussion")
    public String gotoDiscussion() {
        return "discussion/main";
    }


    // 토론 게시글
    @GetMapping("discussion/post")
    public String gotoDiscussionPost() {
        return "discussion/post";
    }


    // 독후감 게시판
    @GetMapping("bookpost")
    public String gotoBookPost() {
        return "post/post-list";
    }


    // 독후감 게시글
    @GetMapping("bookpost/post")
    public String gotoBookPostPost() {
        return "post/post-detail";
    }


    // 독후감 작성
    @GetMapping("bookpost/write")
    public String gotoBookPostWrite() {
        return "post/post-write";
    }

    // 독후감 수정
    @GetMapping("bookpost/edit")
    public String gotoBookPostEdit() {
        return "post/post-update";
    }
}
