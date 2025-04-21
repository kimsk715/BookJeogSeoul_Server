package com.app.bookJeog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper {
    //    책 찜하기
    public void insertBookScrap(Long memberId, String bookIsbn);

    //    특정 회원의 스크랩 여부 조회
    public int isBookScrapped(Long memberId, String bookIsbn);

    //    특정 책의 찜 개수 조회
    public int selectThisScrapCount(String bookIsbn);

    //    책 스크랩 취소
    public void deleteBookScrap(Long memberId, String bookIsbn);
}

