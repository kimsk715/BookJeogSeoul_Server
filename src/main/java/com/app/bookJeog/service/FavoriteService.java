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

    //    독후감 좋아요하기
    public void setBookPostLike(Long memberId, Long bookPostId);

    //    특정 독후감의 내 좋아요 여부 조회
    public int isBookPostLiked(Long memberId, Long bookPostId);

    //    독후감 좋아요 취소하기
    public void deleteBookPostLike(Long memberId, Long bookPostId);

    //    특정 회원 팔로우하기
    public void setMemberFollow(Long receiverId, Long senderId);

    //    내 특정 회원 팔로우여부 조회
    public int isMemberFollowed(Long receiverId, Long senderId);

    //    팔로우 취소
    public void deleteMemberFollow(Long receiverId, Long senderId);
}
