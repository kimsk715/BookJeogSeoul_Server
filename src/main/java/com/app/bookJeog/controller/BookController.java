package com.app.bookJeog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/book/*")
@RequiredArgsConstructor
public class BookController implements BookControllerDocs {

    // 도서 상세정보
    @GetMapping("detail")
    public String gotoBookDetail() {
        return "book/book-detail";
    }

    // 도서 상세정보 - 이 도서의 독후감들
    @GetMapping("detail/posts")
    public String gotoBookDetailPosts() {
        return "book/book-detail-post";
    }
}
