package com.app.bookJeog.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 책 상세정보 API 호출
    @Override
    public String getBookDetail(String isbn) {
        String apiKey = "ttbsuehanh1551001";
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemLookUp.aspx" +
                "?ttbkey=" + apiKey +
                "&itemIdType=ISBN13" +
                "&ItemId=" + isbn +
                "&output=JS&Version=20131101";

        // http 통신을 위한 클래스, 외부 도메인에서 데이터를 가져오는데에 사용
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(apiUrl, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 책 상세정보를 모델에 추가
    @Override
    public void parseAndAddBookInfoToModel(String response, Model model) {
        try {
            // 전달받은 json의 특정 필드에 쉽게 접근하기 위한 클래스
            JsonNode root = objectMapper.readTree(response);
            JsonNode item = root.path("item");

            // item이 배열인지 확인하고, 첫 번째 책 정보 가져오기
            if (item.isArray() && item.size() > 0) {
                JsonNode bookData = item.get(0);  // 첫 번째 책 정보

                String title = bookData.path("title").asText();
                String author = bookData.path("author").asText();
                String publisher = bookData.path("publisher").asText();
                String pubDate = bookData.path("pubDate").asText();
                String description = bookData.path("description").asText();
                String cover = bookData.path("cover").asText();

                // 모델에 추가
                model.addAttribute("title", title);
                model.addAttribute("author", author);
                model.addAttribute("publisher", publisher);
                model.addAttribute("pubDate", pubDate);
                model.addAttribute("description", description);
                model.addAttribute("cover", cover);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

