package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/search/*")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    // 검색창 이동
    @GetMapping("window")
    public String gotoSearch() {
        return "main/search-window";
    }

    // 검색결과 이동
    @GetMapping("result")
    public String gotoSearchResult(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword); // view에 넘겨줌
        return "main/search-result";
    }

    // 통합검색-도서 데이터(REST)
    @GetMapping("api/book-list")
    @ResponseBody
    public Map<String, Object> getBooksByKeyword(@RequestParam("keyword") String keyword) {
        return searchService.searchBooksByKeyword(keyword);
    }

    // 검색결과-도서
    @GetMapping("result/books")
    @ResponseBody
    public String gotoSearchResultBooks() {
        return "main/search-result-book";
    }

    // 검색결과-독후감
    @GetMapping("result/book-posts")
    @ResponseBody
    public String gotoSearchResultBookPosts() {
        return "main/search-result-post";
    }

    // 통합검색-독후감 데이터(REST)
    @GetMapping("api/book-post-list")
    @ResponseBody
    public List<BookPostMemberDTO> getPostsByKeyword(@RequestParam("keyword") String keyword) {
        return searchService.searchBookPosts(keyword);
    }

    // 검색결과-토론방
    @GetMapping("result/discussions")
    @ResponseBody
    public String gotoSearchResultDiscussions() {
        return "main/search-result-discussion";
    }

    // 검색결과-기부단체
    @GetMapping("result/sponsors")
    @ResponseBody
    public String gotoSearchResultSponsors() {
        return "main/search-result-organization";
    }

    // 검색결과-기부글
    @GetMapping("result/donations")
    @ResponseBody
    public String gotoSearchResultDonations() {
        return "main/search-result-donate";
    }
}
