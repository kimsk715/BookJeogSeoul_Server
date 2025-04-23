package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.domain.dto.DiscussionPostDTO;
import com.app.bookJeog.domain.dto.ReceiverPostDTO;
import com.app.bookJeog.domain.dto.SponsorMemberProfileDTO;
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

    // 통합검색-기부단체 데이터(REST)
    @GetMapping("api/sponsor-list")
    @ResponseBody
    public Map<String, Object> getSponsorsByKeyword(@RequestParam("keyword") String keyword) {
        List<SponsorMemberProfileDTO> previewList = searchService.findSponsorMembersWithProfile(keyword);
        int totalCount = searchService.findSponsorMembersTotal(keyword);

        // map 타입으로 결과 List랑 int totalCount 같이 반환
        return Map.of(
                "sponsors", previewList,
                "totalCount", totalCount
        );
    }

    // 통합검색-기부글 데이터(REST)
    @GetMapping("api/receiver-list")
    @ResponseBody
    public Map<String, Object> getReceiversByKeyword(@RequestParam("keyword") String keyword) {
        List<ReceiverPostDTO> previewList = searchService.searchReceivers(keyword);
        int totalCount = searchService.findAllReceiverCount(keyword);

        // map 타입으로 결과 List랑 int totalCount 같이 반환
        return Map.of(
                "receivers", previewList,
                "totalCount", totalCount
        );
    }

    // 검색결과-도서페이지
    @GetMapping("result/books")
    public String gotoSearchResultBooks(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
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

    // 통합검색-토론글 데이터(REST)
    @GetMapping("api/discussion-list")
    @ResponseBody
    public Map<String, Object> getDiscussionsByKeyword(@RequestParam("keyword") String keyword) {
        List<DiscussionPostDTO> previewList = searchService.searchDiscussions(keyword);
        int totalCount = searchService.findAllDiscussionCount(keyword);

        // map 타입으로 결과 List랑 int totalCount 같이 반환
        return Map.of(
                "discussions", previewList,
                "totalCount", totalCount
        );
    }

    // 검색결과-독후감페이지
    @GetMapping("result/book-posts")
    public String gotoSearchResultBookPosts(@RequestParam String keyword, Model model) {
        model.addAttribute("keyword", keyword);
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

    // 검색결과-토론방 페이지
    @GetMapping("result/discussions")
    public String gotoSearchResultDiscussions() {
        return "search/search-result-discussion";
    }

    // 검색결과 - 토론글 페이지 데이터(REST)
    @GetMapping("api/result/discussions")
    @ResponseBody
    public Map<String, Object> getAllDiscussions(@RequestParam String keyword,
                                              @RequestParam(defaultValue = "0") int offset,
                                              @RequestParam(defaultValue = "new") String sortType) {
        List<DiscussionPostDTO> fullList = searchService.findAllDiscussion(keyword, offset, sortType);
        int totalCount = searchService.findAllDiscussionCount(keyword);

        return Map.of(
                "discussions", fullList,
                "totalCount", totalCount
        );
    }

    // 검색결과-기부단체 페이지
    @GetMapping("result/sponsors")
    public String gotoSearchResultSponsors(@RequestParam String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "search/search-result-organization";
    }

    // 검색결과 - 기부단체 페이지 데이터(REST)
    @GetMapping("api/result/sponsors")
    @ResponseBody
    public Map<String, Object> getAllSponsors(@RequestParam String keyword,
                                               @RequestParam(defaultValue = "0") int offset,
                                               @RequestParam(defaultValue = "new") String sortType) {
        List<SponsorMemberProfileDTO> fullList = searchService.findAllSponsorMembers(keyword, offset, sortType);
        int totalCount = searchService.findSponsorMembersTotal(keyword);

        return Map.of(
                "sponsors", fullList,
                "totalCount", totalCount
        );
    }
    
    // 검색결과-기부글
    @GetMapping("result/donations")
    public String gotoSearchResultDonations() {
        return "search/search-result-donate";
    }

    // 검색결과 - 기부글 페이지 데이터(REST)
    @GetMapping("api/result/receivers")
    @ResponseBody
    public Map<String, Object> getAllReceivers(@RequestParam String keyword,
                                              @RequestParam(defaultValue = "0") int offset,
                                              @RequestParam(defaultValue = "new") String sortType) {
        List<ReceiverPostDTO> fullList = searchService.findAllReceivers(keyword, offset, sortType);
        int totalCount = searchService.findAllReceiverCount(keyword);

        return Map.of(
                "receivers", fullList,
                "totalCount", totalCount
        );
    }
}
