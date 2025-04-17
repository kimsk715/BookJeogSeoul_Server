package com.app.bookJeog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/search/*")
@RequiredArgsConstructor
public class SearchController {

    // 검색창 이동
    @GetMapping("window")
    public String gotoSearch() {
        return "main/search-window";
    }

    // 검색결과 이동
    @GetMapping("result")
    public String gotoSearchResult() {
        return "main/search-result";
    }

    // 검색결과-도서
    @GetMapping("result/books")
    public String gotoSearchResultBooks() {
        return "main/search-result-book";
    }

    // 검색결과-독후감
    @GetMapping("result/book-posts")
    public String gotoSearchResultBookPosts() {
        return "main/search-result-post";
    }

    // 검색결과-토론방
    @GetMapping("result/discussions")
    public String gotoSearchResultDiscussions() {
        return "main/search-result-discussion";
    }

    // 검색결과-기부단체
    @GetMapping("result/sponsors")
    public String gotoSearchResultSponsors() {
        return "main/search-result-organization";
    }

    // 검색결과-기부글
    @GetMapping("result/donations")
    public String gotoSearchResultDonations() {
        return "main/search-result-donate";
    }
}
