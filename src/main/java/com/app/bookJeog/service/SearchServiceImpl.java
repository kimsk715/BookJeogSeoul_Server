package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.domain.dto.DiscussionPostDTO;
import com.app.bookJeog.domain.dto.SponsorMemberProfileDTO;
import com.app.bookJeog.repository.MemberDAO;
import com.app.bookJeog.repository.PostDAO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements SearchService {
    private final PostDAO postDAO;
    private final MemberDAO memberDAO;

    // 알라딘 api로 도서검색
    public Map<String, Object> searchBooksByKeyword(String keyword) {
        String apiKey = "ttbsuehanh1551001";
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx" +
                "?ttbkey=" + apiKey +
                "&Query=" + keyword +
                "&QueryType=Keyword" +
                "&SearchTarget=Book" +
                "&Sort=Accuracy" +
                "&MaxResults=6" +
                "&start=1" +
                "&Cover=Big" +
                "&output=JS&Version=20131101";

        RestTemplate restTemplate = new RestTemplate();  // 외부 API 요청 도구
        Map<String, Object> result = new HashMap<>();
        List<Map<String, String>> bookList = new ArrayList<>(); // 결과 데이터를 담을 리스트

        try {
            // API 요청 및 응답 받기
            String response = restTemplate.getForObject(apiUrl, String.class);

            // 응답 문자열을 JSON 객체로 파싱
            // 파싱: 받은 문자열 데이터를 사용할 수 있게 변환하는 작업
            JsonNode root = new ObjectMapper().readTree(response);
            int totalResults = root.path("totalResults").asInt();
            JsonNode items = root.path("item");

            // 배열 형태의 응답이 맞는지 확인 후 반복 처리
            if (items.isArray()) {
                for (JsonNode item : items) {
                    Map<String, String> book = new HashMap<>();
                    book.put("title", item.path("title").asText());
                    book.put("author", item.path("author").asText());
                    book.put("isbn13", item.path("isbn13").asText());
                    book.put("cover", item.path("cover").asText());

                    bookList.add(book);
                }
            }

            result.put("totalResults", totalResults); // ✅ 결과 개수 담기
            result.put("books", bookList);            // ✅ 책 리스트 담기


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // 통합 독후감 검색결과(최신순 3개)
    public List<BookPostMemberDTO> searchBookPosts(String keyword) {
        int totalCount = postDAO.findBookPostsCount(keyword); // 독후감 개수
        List<BookPostMemberDTO> posts = postDAO.searchBookPosts(keyword); // 독후감 내용

        // 각 DTO에 count 값을 추가로 세팅
        for (BookPostMemberDTO post : posts) {
            post.setCount(totalCount);
        }
        return posts;
    }

    // 독후감 전체목록 조회(무한스크롤)
    public Map<String, Object> getAllBooksWithCount(String keyword, int offset, String sortType) {
        List<BookPostMemberDTO> posts = postDAO.findAllBooks(keyword, offset, sortType); // 목록
        int totalCount = postDAO.findAllBooksCount(keyword); // 개수

        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        result.put("totalCount", totalCount);

        return result;
    }

    // 기업회원 통합검색 조회
    public List<SponsorMemberProfileDTO> findSponsorMembersWithProfile(String keyword) {
        return memberDAO.findSponsorMembersWithProfile(keyword);
    }

    // 기업회원 검색 결과 개수 조회
    public int findSponsorMembersTotal(String keyword){
        return memberDAO.findSponsorMembersTotal(keyword);
    }

    // 기업회원 전체페이지 조회(무한스크롤)
    public List<SponsorMemberProfileDTO> findAllSponsorMembers(String keyword, int offset, String sortType){
        return memberDAO.findAllSponsorMembers(keyword, offset, sortType);
    }

    // 검색한 검색어에 맞는 토론글 통합검색 조회
    public List<DiscussionPostDTO> searchDiscussions(String keyword){
        return postDAO.searchDiscussions(keyword);
    };

    // 검색한 검색어에 맞는 토론글 통합검색 개수 조회
    public int findAllDiscussionCount(String keyword){
        return postDAO.findAllDiscussionCount(keyword);
    };

    // 검색한 검색어에 맞는 토론글 무한스크롤로 출력
    public List<DiscussionPostDTO> findAllDiscussion(String keyword, int offset, String sortType){
        return postDAO.findAllDiscussion(keyword, offset, sortType);
    };
}
