package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.dto.ReceiverLikeDTO;
import com.app.bookJeog.domain.vo.ReceiverLikeVO;

import java.util.List;

public interface FavoriteService {

    // 책 스크랩하기 버튼
    void scrap(Long bookIsbn);

    // 특정 책의 스크랩 수 조회
    int getScrapCount(Long isbn);

    // 특정 회원의 스크랩 여부 조회
    boolean selectMemberScrap(Long isbn);

    // 책 스크랩 취소
    void deleteScrap(Long isbn);

    public Long topReceiver();

    //    독후감 좋아요하기
    public void setBookPostLike(Long bookPostId);

    //    특정 독후감의 내 좋아요 여부 조회
    public boolean isBookPostLiked(Long bookPostId);

    //    독후감 좋아요 취소하기
    public void deleteBookPostLike(Long bookPostId);

    //    특정 회원 팔로우하기
    public void setMemberFollow(Long receiverId);

    //    내 특정 회원 팔로우여부 조회
    public boolean isMemberFollowed(Long receiverId);

    //    팔로우 취소
    public void deleteMemberFollow(Long receiverId);

    // 특정 독후감의 좋아요 개수 조회
    public int countBookPostLike(Long bookPostId);

    // 마이페이지의 스크랩 도서 전체목록(무한스크롤)
    public List<Long> getScrappedIsbnList(Long memberId, int offset, String orderType);
    public void voteToReceiver(ReceiverLikeVO receiverLikeVO);

    public int countAllVoteByPostId(ReceiverLikeDTO receiverLikeDTO);
}
