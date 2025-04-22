package com.app.bookJeog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper {
    //    책 찜하기
    public void insertBookScrap(Long memberId, Long bookIsbn);

    //    특정 회원의 스크랩 여부 조회
    public int isBookScrapped(Long memberId, Long bookIsbn);

    //    특정 책의 찜 개수 조회
    public int selectThisScrapCount(Long bookIsbn);

    //    책 스크랩 취소
    public void deleteBookScrap(Long memberId, Long bookIsbn);
}

