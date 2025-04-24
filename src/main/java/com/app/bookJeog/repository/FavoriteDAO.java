package com.app.bookJeog.repository;

import com.app.bookJeog.mapper.FavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FavoriteDAO {
    private final FavoriteMapper favoriteMapper;

    //    책 찜하기
    public void setBookScrap(Long memberId, Long bookIsbn){
        favoriteMapper.insertBookScrap(memberId, bookIsbn);
    };

    //    특정 회원의 스크랩 여부 조회
    public int findMemberScrap(Long memberId, Long bookIsbn) {
        return favoriteMapper.isBookScrapped(memberId, bookIsbn);
    }

    //    특정 책의 찜 개수 조회
    public int findThisScrapCount(Long bookIsbn){
        return favoriteMapper.selectThisScrapCount(bookIsbn);
    };

    //    책 스크랩 취소
    public void deleteBookScrap(Long memberId, Long bookIsbn){
        favoriteMapper.deleteBookScrap(memberId, bookIsbn);
    };

}
