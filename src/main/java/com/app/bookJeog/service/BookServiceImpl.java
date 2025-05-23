package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookDTO;
import com.app.bookJeog.domain.dto.BrailleBookDTO;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.domain.dto.FileBookPostDTO;
import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import com.app.bookJeog.repository.BookDAO;
import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;
    @Override
    public void insertTempSelectedBook(Long isbn) {
        bookDAO.insertTempSelectedBook(isbn);
    }

    @Override
    public List<TempSelectedBookVO> getTempSelectedBook() {
        return bookDAO.findTempSelectedBook();
    }

    @Override
    public void insertSelectedBook(SelectedBookVO selectedBookVO) {
        bookDAO.insertSelectedBook(selectedBookVO);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();





    // 책 상세정보 API 호출
    @Override
    public String getBookDetail(String isbn) {
        String apiKey = "ttbsuehanh1551001";
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemLookUp.aspx" +
                "?ttbkey=" + apiKey +
                "&itemIdType=ISBN13" +
                "&ItemId=" + isbn +
                "&Cover=Big" +
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
//                    log.info(categoryName);
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

    // 이 작가의 다른 책 목록
    @Override
    public String getBooksByAuthor(String encodedAuthor) {
        String apiKey = "ttbsuehanh1551001";
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx" +
                "?ttbkey=" + apiKey +
                "&Query=" + encodedAuthor +
                "&QueryType=Author" +
                "&SearchTarget=Book" +
                "&MaxResults=9" +
                "&Start=1" +
                "&Sort=Accuracy" +
                "&Cover=Big" +
                "&output=JS" +
                "&Version=20131101";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }


    // 인기도서 최다조회
    @Override
    public List<MemberHistoryVO> selectTopViewBooks() {
        return bookDAO.findTopViewBook();
    }


    // 관리자 추천도서
    @Override
    public List<SelectedBookVO> selectAdminSuggestBooks() {
        return bookDAO.findAdminSuggestBook();
    }


    // 인기 독후감 조회
    @Override
    public List<BookPostVO> selectTopBookPost() {
        return bookDAO.findTopBookPost();
    }



    // 선정 도서 여부 조회
    public Long findSelectedBooks(Long bookIsbn){
        return bookDAO.findSelectedBooks(bookIsbn)
                .map(SelectedBookVO::getId)
                .orElse(null);
    };


    // 전체 도서 isbn과 줄거리 가져오기
    public List<BookDTO> findIsbnAndSummary() {
        return bookDAO.findIsbnAndSummary().stream()
                .map(this::toBookDTO) // 여기서 this는 BookService
                .collect(Collectors.toList()); // List로 변환
    }

    // 최근 조회한 도서 10개 줄거리와 함께
    public String findBookSummaryToString(Long memberId){
        return bookDAO.findBookSummaryToString(memberId);
    }

    @Override
    public List<BrailleBookDTO> getBrailleBooks() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.odcloud.kr/api/15004147/v1/uddi:d35c9e96-d405-4352-80d5-94dd7b134913";
        String rawServiceKey = "K/kXjZc7XJr5t9u3YIf8H6FPEUNjmaiYXUdakteMHXnE9YmSvd8P7fKbyEh1VL6Gih0l8qY/jtHl3MRnqYoHvA==";

        String finalUrl = url + "?page=1&perPage=30&serviceKey=" + rawServiceKey;

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                finalUrl,
                HttpMethod.GET,
                entity,
                JsonNode.class
        );

        JsonNode root = response.getBody();

        if (root != null) {
            JsonNode dataNode = root.path("data");
            try {
                if (dataNode != null && !dataNode.isMissingNode()) {
                    List<BrailleBookDTO> books = objectMapper.readValue(
                            dataNode.traverse(),
                            new TypeReference<List<BrailleBookDTO>>() {}
                    );
                    return books;
                } else {
                    throw new RuntimeException("No data found in response");
                }
            } catch (Exception e) {
                throw new RuntimeException("점자책 가져오는데 실패했음", e);
            }
        } else {
            throw new RuntimeException("API 응답이 없습니다.");
        }
    }


}





