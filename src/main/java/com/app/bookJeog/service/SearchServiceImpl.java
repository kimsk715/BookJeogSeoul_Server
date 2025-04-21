package com.app.bookJeog.service;

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

    
}
