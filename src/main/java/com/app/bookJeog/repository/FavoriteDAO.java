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

    public int receiverVote(Long receiverId){
        return favoriteMapper.receiverVote(receiverId);
    }

    public Long topReceiver(){
        return favoriteMapper.topReceiver();
    }

    //    독후감 좋아요하기
    public void setBookPostLike(Long memberId, Long bookPostId){
        favoriteMapper.insertBookPostLike(memberId, bookPostId);
    };

    //    특정 독후감의 내 좋아요 여부 조회
    public int isBookPostLiked(Long memberId, Long bookPostId){
        return favoriteMapper.isBookPostLiked(memberId, bookPostId);
    };

    //    독후감 좋아요 취소하기
    public void deleteBookPostLike(Long memberId, Long bookPostId){
        favoriteMapper.deleteBookPostLike(memberId, bookPostId);
    };

    //    특정 회원 팔로우하기
    public void setMemberFollow(Long receiverId, Long senderId){
        favoriteMapper.insertMemberFollow(receiverId, senderId);
    };

    //    내 특정 회원 팔로우여부 조회
    public int isMemberFollowed(Long receiverId, Long senderId){
        return favoriteMapper.isMemberFollowed(receiverId, senderId);
    };

    //    팔로우 취소
    public void deleteMemberFollow(Long receiverId, Long senderId){
        favoriteMapper.deleteMemberFollow(receiverId, senderId);
    };

    // 특정 독후감의 좋아요 개수 조회
    public int countBookPostLike(Long bookPostId){
        return favoriteMapper.countBookPostLike(bookPostId);
    };
}
