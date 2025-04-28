package com.app.bookJeog.service;

import com.app.bookJeog.controller.exception.UnauthenticatedException;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.repository.FavoriteDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDAO favoriteDAO;
    private final HttpSession session;

    // 세션에서 로그인된 회원의 ID를 반환. 없을 경우 예외 발생
    private Long getSessionMemberId() {
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        if (member == null) {
            throw new UnauthenticatedException("로그인이 필요한 서비스입니다.");
        }
        return member.getId();
    }

    // 특정 회원의 스크랩 여부 조회
    public boolean selectMemberScrap(Long bookIsbn) {
        Long memberId = getSessionMemberId();
        return favoriteDAO.findMemberScrap(memberId, bookIsbn) > 0;
    }

    // 책 스크랩하기 버튼
    public void scrap(Long bookIsbn) {
        Long memberId = getSessionMemberId();
        boolean alreadyScrapped = favoriteDAO.findMemberScrap(memberId, bookIsbn) > 0;

        if (!alreadyScrapped) {
            favoriteDAO.setBookScrap(memberId, bookIsbn);
        } else {
            log.info("이미 스크랩된 책");
        }
    }

    // 특정 책의 스크랩 수 조회
    public int getScrapCount(Long isbn) {
        return favoriteDAO.findThisScrapCount(isbn);
    }

    // 책 스크랩 취소
    public void deleteScrap(Long isbn) {
        Long memberId = getSessionMemberId();
        favoriteDAO.deleteBookScrap(memberId, isbn);
    }

    @Override
    public Long topReceiver() {
        return favoriteDAO.topReceiver();
    }

    ;
    // 독후감 좋아요하기
    public void setBookPostLike(Long bookPostId) {
        Long memberId = getSessionMemberId();
        favoriteDAO.setBookPostLike(memberId, bookPostId);
    }

    // 특정 독후감의 내 좋아요 여부 조회
    public boolean isBookPostLiked(Long bookPostId) {
        Long memberId = getSessionMemberId();
        return favoriteDAO.isBookPostLiked(memberId, bookPostId) > 0;
    }

    // 독후감 좋아요 취소하기
    public void deleteBookPostLike(Long bookPostId) {
        Long memberId = getSessionMemberId();
        favoriteDAO.deleteBookPostLike(memberId, bookPostId);
    }

    // 특정 회원 팔로우하기
    public void setMemberFollow(Long receiverId) {
        Long senderId = getSessionMemberId();
        favoriteDAO.setMemberFollow(receiverId, senderId);
    }

    // 내 특정 회원 팔로우 여부 조회
    public boolean isMemberFollowed(Long receiverId) {
        Long senderId = getSessionMemberId();
        return favoriteDAO.isMemberFollowed(receiverId, senderId) > 0;
    }

    // 팔로우 취소
    public void deleteMemberFollow(Long receiverId) {
        Long senderId = getSessionMemberId();
        favoriteDAO.deleteMemberFollow(receiverId, senderId);
    }

    // 특정 독후감의 좋아요 개수 조회
    public int countBookPostLike(Long bookPostId){
        return favoriteDAO.countBookPostLike(bookPostId);
    };
}
