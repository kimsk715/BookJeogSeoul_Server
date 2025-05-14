package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.AladinBookDTO;
import com.app.bookJeog.domain.dto.BrailleBookDTO;
import com.app.bookJeog.domain.dto.FileBookPostDTO;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AladinService {
    // ISBN으로 알라딘 API에서 책 정보를 조회하여 JSON으로 반환
    JSONObject getBookInfo(Long isbn) throws JSONException;

    // ISBN으로 알라딘 API에서 커버 이미지 URL만 반환
    String getBookCover(Long isbn);

    // 알라딘 API로 요청을 보내고, item 배열 중 첫 번째 책 정보를 반환
    JSONObject fetchItem(Long isbn);

    // 검색 키워드로 전체 결과 개수와 도서 목록을 포함한 JSON 객체 반환
    JSONObject searchBooks(String keyword, int startIndex, int maxResults, String sort);

    // 검색 키워드로 알라딘 API에서 도서 정보와 총 결과 수를 받아와 Map<String, Object> 형태로 반환
    public Map<String, Object> searchBooksToMap(String keyword, int startIndex, int maxResults, String sort) throws JSONException;

    // 알라딘에서 인기도서 9개 조회
    public JSONObject searchBestBooks();
    
    // 단일 도서 정보를 isbn으로 가져와 Map 타입으로 반환
    public Map<String, Object> getBookInfoAsMap(Long isbn);

    // 인기 도서 정보를 Map 타입으로 반환
    Map<String, Object> convertBestBooksToSimpleMap() throws JSONException;

    // 여러개의 도서 정보를 isbn으로 가져와 List로 반환
    public List<AladinBookDTO> getBooksByIsbnList(List<Long> isbnList);

    default AladinBookDTO getBooksByTitle(String title, String author) {
        String baseUrl = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx"; // LookUp 아님
        String ttbkey = "ttbsuehanh1551001";

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?ttbkey=").append(ttbkey)
                .append("&Query=").append(title).append("+").append(author)
                .append("&QueryType=Keyword")
                .append("&MaxResults=10")
                .append("&start=1")
                .append("&output=js")  // ★ JSON 응답 받기 위해 반드시 필요
                .append("&Version=20131101");

        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(urlBuilder.toString(), String.class);

            // 응답 확인
            System.out.println("API Response: " + response);

            JSONObject json = new JSONObject(response);
            JSONArray items = json.optJSONArray("item");

            if (items != null && items.length() > 0) {
                JSONObject item = items.getJSONObject(0);
                AladinBookDTO moelList = new AladinBookDTO();

                moelList.setBookIsbn(item.optLong("isbn13"));
                moelList.setBookTitle(item.optString("title"));
                moelList.setBookAuthor(item.optString("author"));
                moelList.setBookPublisher(item.optString("publisher"));
                moelList.setBookCover(item.optString("cover"));

                return moelList;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("API 사용 실패: " + e.getMessage());
        }
        return null;
    }





    // ISBN 리스트로 도서 정보를 정렬하여 조회
    default AladinBookDTO fetchSortedBook(Long isbn, String sort) {
        String baseUrl = "https://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";
        String ttbkey = "YOUR_ALADIN_TTB_KEY";

        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append("?ttbkey=").append(ttbkey)
                .append("&itemIdType=ISBN&ItemId=").append(isbn)
                .append("&output=js");

        if (sort != null && !sort.equals("default")) {
            urlBuilder.append("&sort=").append(sort);
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(urlBuilder.toString(), String.class);

            JSONObject json = new JSONObject(response);
            JSONArray items = json.optJSONArray("item");

            if (items != null && items.length() > 0) {
                JSONObject item = items.getJSONObject(0);
                AladinBookDTO dto = new AladinBookDTO();
                dto.setBookIsbn(isbn);
                dto.setBookTitle(item.optString("title"));
                dto.setBookAuthor(item.optString("author"));
                dto.setBookPublisher(item.optString("publisher"));
                dto.setBookCover(item.optString("cover"));
                return dto;
            }

        } catch (Exception e) {
            System.err.println("Failed to fetch book for ISBN: " + isbn);
        }

        return null;
    }

    List<AladinBookDTO> getSortedBooksByIsbnList(List<Long> isbnList, String sort);
}
