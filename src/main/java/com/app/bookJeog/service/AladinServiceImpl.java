package com.app.bookJeog.service;

<<<<<<< HEAD
import com.app.bookJeog.domain.dto.AladinBookDTO;
import com.app.bookJeog.domain.dto.FileBookPostDTO;
import com.google.gson.JsonObject;
=======
>>>>>>> master
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class AladinServiceImpl implements AladinService {
    // 알라딘 API Key
    private static final String ALADIN_API_KEY = "ttbkimsk7151659001";

    // 알라딘 API 요청 경로
    private static final String API_URL = "https://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";

    // 커버 이미지가 없을 경우 사용할 기본 이미지 경로
    private static final String DEFAULT_COVER = "/images/common/default-book-cover.png";

    // ISBN으로 알라딘 API에서 책 정보를 조회하여 JSONObject로 반환
    public JSONObject getBookInfo(Long isbn) throws JSONException {
        JSONObject item = fetchItem(isbn);
        if (item == null) return new JSONObject();

        JSONObject result = new JSONObject();
        result.put("isbn", isbn); // ISBN 그대로 출력
        result.put("title", item.optString("title")); // 책 제목
        result.put("author", item.optString("author")); // 작가
        result.put("publisher", item.optString("publisher")); // 출판사
        result.put("description", item.optString("description")); // 책 설명
        result.put("cover", item.optString("cover")); // 커버 이미지 URL
        return result;
    }


    // ISBN으로 알라딘 API에서 커버 이미지 URL만 반환
    public String getBookCover(Long isbn) {
        JSONObject item = fetchItem(isbn);
        return item != null ? item.optString("cover") : DEFAULT_COVER;
    }


    // 알라딘 API로 요청을 보내고, item 배열 중 첫 번째 책 정보를 반환
    public JSONObject fetchItem(Long isbn) {
        try {
            // 요청 URL 구성
            String urlStr = API_URL +
                    "?ttbkey=" + ALADIN_API_KEY +
                    "&itemIdType=ISBN13" +
                    "&ItemId=" + isbn +
                    "&output=JS" +
                    "&Version=20131101" +
                    "&Cover=Big";

            // URL 연결 생성
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 응답 읽기
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String result = br.lines().collect(Collectors.joining());

                // 응답 문자열을 JSON으로 변환
                JSONObject json = new JSONObject(result);
                if (!json.has("item")) {
                    log.warn("Aladin 응답에 'item' 키가 없음: {}", json);
                    return null;
                }

                JSONArray items = json.getJSONArray("item");

                // 첫 번째 아이템이 존재하면 반환
                return items.length() > 0 ? items.getJSONObject(0) : null;
            }

        } catch (Exception e) {
            // 에러 출력 및 null 반환
            e.printStackTrace();
            return null;
        }
    }

    // 검색 키워드로 전체 결과 개수와 도서 목록을 포함한 JSON 객체 반환
    public JSONObject searchBooks(String keyword, int startIndex, int maxResults, String sort){
        try {
            // 알라딘 ItemSearch API 요청 URL 구성
            String urlStr = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx"
                    + "?ttbkey=" + ALADIN_API_KEY                          // API 인증 키
                    + "&Query=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8)     // 검색 키워드 (URL 인코딩)
                    + "&QueryType=Keyword"                                // 키워드 기반 검색
                    + "&SearchTarget=Book"                                // 검색 대상: 도서
                    + "&output=JS"                                        // 응답 형식: JSON
                    + "&Sort=" + sort
                    + "&Version=20131101"
                    + "&start=" + startIndex
                    + "&Cover=Big"
                    + "&maxResults=" + maxResults;

            // HTTP 연결 생성
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 응답 읽기
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                // 응답 내용을 문자열로 조립
                String result = br.lines().collect(Collectors.joining());

                // 문자열을 JSONObject로 변환
                JSONObject json = new JSONObject(result);

                // 응답 객체에는 총 검색 결과 수(totalResults), 도서 목록(item 배열) 등이 포함됨
                return json;
            }

        } catch (Exception e) {
            e.printStackTrace(); // 에러 로그 출력
            return null;
        }
    }

    // 상위 9개의 인기도서 json으로 반환
    public JSONObject searchBestBooks(){
        try {
            // 알라딘 ItemSearch API 요청 URL 구성
            String urlStr = "https://www.aladin.co.kr/ttb/api/ItemList.aspx"
                    + "?ttbkey=" + ALADIN_API_KEY                          // API 인증 키
                    + "&QueryType=Bestseller"                             // 인기 도서 검색
                    + "&MaxResults=9"
                    + "&SearchTarget=Book"                                // 검색 대상: 도서
                    + "&output=JS"                                        // 응답 형식: JSON
                    + "&start=1"
                    + "&Cover=Big"
                    + "&Version=20131101";

            // HTTP 연결 생성
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 응답 읽기
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                // 응답 내용을 문자열로 조립
                String result = br.lines().collect(Collectors.joining());

                // 문자열을 JSONObject로 변환
                JSONObject json = new JSONObject(result);

                // 응답 객체에는 총 검색 결과 수(totalResults), 도서 목록(item 배열) 등이 포함됨
                return json;
            }

        } catch (Exception e) {
            e.printStackTrace(); // 에러 로그 출력
            return null;
        }
    }

    // 인기도서 json Map형태로 변환
    public Map<String, Object> convertBestBooksToSimpleMap() throws JSONException {
        JSONObject json = searchBestBooks(); // Bestseller API 호출

        List<Map<String, Object>> bookList = new ArrayList<>();
        JSONArray items = json.optJSONArray("item");

        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                JSONObject book = items.getJSONObject(i);
                Map<String, Object> bookMap = new HashMap<>();

                bookMap.put("title", book.optString("title"));
                bookMap.put("author", book.optString("author"));
                bookMap.put("cover", book.optString("cover"));
                bookMap.put("isbn", book.optString("isbn13")); // 그대로 키 유지

                bookList.add(bookMap);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("item", bookList); // 도서 리스트만 포함
        return result;
    }

    // json을 Map<String, Object> 형태로 반환
    public Map<String, Object> searchBooksToMap(String keyword, int startIndex, int maxResults, String sort) throws JSONException {
        // 알라딘 API에서 응답 JSON 받아오기
        JSONObject json = searchBooks(keyword, startIndex, maxResults, sort);

        // 도서 목록을 담을 리스트
        List<Map<String, Object>> bookList = new ArrayList<>();

        // 응답 JSON에서 item 배열 추출
        JSONArray items = json.optJSONArray("item");

        if (items != null) {
            for (int i = 0; i < items.length(); i++) {
                JSONObject book = items.getJSONObject(i);

                // 각 도서 정보를 Map으로 변환
                Map<String, Object> bookMap = new HashMap<>();
                // optString: 값 추출 메소드
                bookMap.put("title", book.optString("title"));              // 도서 제목
                bookMap.put("author", book.optString("author"));            // 저자
                bookMap.put("cover", book.optString("cover"));              // 커버 이미지 URL
                bookMap.put("isbn", book.optString("isbn13"));              // ISBN13
                bookMap.put("publisher", book.optString("publisher"));      // 출판사
                bookMap.put("publishDate", book.optString("pubDate"));  // 출판일

                bookList.add(bookMap); // 리스트에 추가
            }
        }

        // 최종 응답 구성: 총 결과 수 + 도서 리스트
        Map<String, Object> result = new HashMap<>();
        result.put("totalResults", json.optInt("totalResults", 0)); // 총 검색 결과 수
        result.put("item", bookList); // 도서 리스트

        return result;
    }
    // 단일 도서 정보를 isbn으로 가져와 Map 타입으로 반환
    public Map<String, Object> getBookInfoAsMap(Long isbn) {
        try {
            JSONObject json = getBookInfo(isbn);
            Map<String, Object> result = new HashMap<>();
            result.put("isbn", json.optLong("isbn13"));
            result.put("title", json.optString("title"));
            result.put("author", json.optString("author"));
            result.put("publisher", json.optString("publisher"));
            result.put("pubDate", json.optString("pubDate"));
            result.put("description", json.optString("description"));
            result.put("cover", json.optString("cover"));
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 여러개의 도서 정보를 isbn으로 가져와 List로 반환
    public List<AladinBookDTO> getBooksByIsbnList(List<Long> isbnList) {
        List<AladinBookDTO> result = new ArrayList<>();

        for (Long isbn : isbnList) {
            try {
                JSONObject item = fetchItem(isbn);
                if (item == null) continue;

                AladinBookDTO dto = new AladinBookDTO();
                dto.setBookIsbn(isbn);
                dto.setBookTitle(item.optString("title"));
                dto.setBookAuthor(item.optString("author"));
                dto.setBookPublisher(item.optString("publisher"));
                dto.setBookCover(item.optString("cover"));

                result.add(dto);
            } catch (Exception e) {
                // 실패한 ISBN은 무시하고 계속 진행
                continue;
            }
        }

        return result;
    }
}