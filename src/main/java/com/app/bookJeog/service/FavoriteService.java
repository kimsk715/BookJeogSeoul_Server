package com.app.bookJeog.service;

public interface FavoriteService {
    // 책 스크랩하기 버튼
    void scrap(Long bookIsbn);

    // 특정 책의 스크랩 수 조회
    int getScrapCount(Long isbn);

    // 특정 회원의 스크랩 여부 조회
    boolean selectMemberScrap(Long isbn);

    // 책 스크랩 취소
    void deleteScrap(Long isbn);
}
