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
    public void deleteBookScrap(Long memberId, String bookIsbn);

    public int receiverVote(Long receiverId);

    public Long topReceiver();

    public void deleteBookScrap(Long memberId, Long bookIsbn);

    //    독후감 좋아요하기
    public void insertBookPostLike(Long memberId, Long bookPostId);

    //    특정 독후감의 내 좋아요 여부 조회
    public int isBookPostLiked(Long memberId, Long bookPostId);

    //    독후감 좋아요 취소하기
    public void deleteBookPostLike(Long memberId, Long bookPostId);

    //    특정 회원 팔로우하기
    public void insertMemberFollow(Long receiverId, Long senderId);

    //    내 특정 회원 팔로우여부 조회
    public int isMemberFollowed(Long receiverId, Long senderId);

    //    팔로우 취소
    public void deleteMemberFollow(Long receiverId, Long senderId);

    // 특정 독후감의 좋아요 개수 조회
    public int countBookPostLike(Long bookPostId);
}

