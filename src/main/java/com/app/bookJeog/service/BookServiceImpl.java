package com.app.bookJeog.service;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;


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

            if (!item.isArray() || item.size() == 0) {
                throw new ResourceNotFoundException("해당 도서를 찾을 수 없습니다.");
            }

            // item이 배열인지 확인하고, 첫 번째 책 정보 가져오기
            if (item.isArray() && item.size() > 0) {
                JsonNode bookData = item.get(0);  // 첫 번째 책 정보

                String title = bookData.path("title").asText();
                String author = bookData.path("author").asText();
                String publisher = bookData.path("publisher").asText();
                String pubDate = bookData.path("pubDate").asText();
                String description = bookData.path("description").asText();
                String cover = bookData.path("cover").asText();

                // 국내도서>어린이>초등 전학년>동화/명작/고전 같은 이름에서 마지막 카테고리명만 추출
                String categoryName = null;

                // categoryName이 null이 아닌지 확인
                categoryName = bookData.path("categoryName").asText();

                // \u003E를 실제 '>' 기호로 변환
                if (categoryName != null) {
                    log.info(categoryName);
                    categoryName = categoryName.replace("\u003E", ">");

                    // 카테고리 구분 처리
                    String[] categories = categoryName.split(">");
                    String lastCategory = categories[categories.length - 1];
                    model.addAttribute("category", lastCategory);
                } else {
                    model.addAttribute("category", "정보 없음");
                }

                // 모델에 추가
                model.addAttribute("title", title);
                model.addAttribute("author", author);
                model.addAttribute("publisher", publisher);
                model.addAttribute("pubDate", pubDate);
                model.addAttribute("description", description);
                model.addAttribute("cover", cover);
            }
        } catch (Exception e) {
            throw new RuntimeException("도서 파싱 중 오류 발생");
        }
    }
}

