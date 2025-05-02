package com.app.bookJeog.service;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

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
}
