package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostMemberDTO;

import java.util.List;
import java.util.Map;

public interface SearchService {

    // 알라딘 api로 도서검색
    public Map<String, Object> searchBooksByKeyword(String keyword);

    // 검색한 검색어에 맞는 독후감 통합검색 조회
    List<BookPostMemberDTO> searchBookPosts(String keyword);

    // 검색한 검색어에 맞는 독후감 무한스크롤로 출력
    public Map<String, Object> getAllBooksWithCount(String keyword, int offset, String sortType);
}
