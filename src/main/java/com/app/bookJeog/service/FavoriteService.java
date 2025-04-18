package com.app.bookJeog.service;

public interface FavoriteService {
    // 책 스크랩하기 버튼
    void scrap(String bookIsbn);

    // 특정 책의 스크랩 수 조회
    int getScrapCount(String isbn);

    // 책 스크랩 취소
    void deleteScrap(String isbn);
}
