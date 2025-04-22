package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.service.AladinService;
import com.app.bookJeog.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
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
    private final AladinService aladinService;

    // 검색창 이동
    @GetMapping("window")
    public String gotoSearch() {
        return "search/search-window";
    }

    // 검색결과 이동
    @GetMapping("result")
    public String gotoSearchResult(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword); // view에 넘겨줌
        return "search/search-result";
    }

    // 통합검색-도서 데이터(REST)
    @GetMapping("api/book-list")
    @ResponseBody
    public Map<String, Object> getBooksByKeyword(@RequestParam("keyword") String keyword) {
        return searchService.searchBooksByKeyword(keyword);
    }

    // 검색결과-도서페이지
    @GetMapping("result/books")
    public String gotoSearchResultBooks() {
        return "search/search-result-book";
    }

    // 검색결과-도서페이지 데이터(REST)
    @GetMapping("api/result/books")
    @ResponseBody
    public Map<String, Object> getAllBooks(@RequestParam String keyword,
                                                  @RequestParam(defaultValue = "1") int startIndex,
                                                  @RequestParam(defaultValue = "35") int maxResults,
                                                  @RequestParam(defaultValue = "Accuracy") String sort) throws JSONException {
        return aladinService.searchBooksToMap(keyword, startIndex, maxResults, sort);
    }

    // 통합검색-독후감 데이터(REST)
    @GetMapping("api/book-post-list")
    @ResponseBody
    public List<BookPostMemberDTO> getPostsByKeyword(@RequestParam("keyword") String keyword) {
        return searchService.searchBookPosts(keyword);
    }

    // 검색결과-독후감페이지
    @GetMapping("result/book-posts")
    public String gotoSearchResultBookPosts() {
        return "search/search-result-post";
    }

    // 검색결과 - 독후감페이지 데이터(REST)
    @GetMapping("api/result/book-posts")
    @ResponseBody
    public Map<String, Object> getAllBookPosts(@RequestParam String keyword,
                                               @RequestParam(defaultValue = "0") int offset,
                                               @RequestParam(defaultValue = "new") String sortType) {
        return searchService.getAllBooksWithCount(keyword, offset, sortType);
    }

    // 검색결과-토론방
    @GetMapping("result/discussions")
    public String gotoSearchResultDiscussions() {
        return "search/search-result-discussion";
    }

    // 검색결과-기부단체
    @GetMapping("result/sponsors")
    public String gotoSearchResultSponsors() {
        return "search/search-result-organization";
    }

    // 검색결과-기부글
    @GetMapping("result/donations")
    public String gotoSearchResultDonations() {
        return "search/search-result-donate";
    }
}
