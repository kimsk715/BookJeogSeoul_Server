package com.app.bookJeog.service;

import com.app.bookJeog.controller.exception.UnauthenticatedException;
import com.app.bookJeog.domain.dto.MemberDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.repository.FavoriteDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDAO favoriteDAO;
    private final HttpSession session;

    // 특정 회원의 스크랩 여부 조회
    public boolean selectMemberScrap(Long bookIsbn){
        MemberDTO member = (MemberDTO)session.getAttribute("member");
        Long memberId = member.getId();

        if (member == null) {
            return false; // 로그인 안 된 경우 false
        } else {
            return favoriteDAO.findMemberScrap(memberId, bookIsbn) > 0;
        }
    };

    // 책 스크랩하기 버튼
    public void scrap(Long bookIsbn){
        MemberDTO member = (MemberDTO) session.getAttribute("member");

        if (member == null) {
            throw new UnauthenticatedException("로그인이 필요한 서비스입니다.");
        }

        Long memberId = member.getId(); // 세션에서 아이디 받아옴

        // 혹시 이미 스크랩을 했는지 조회(중복 insert 방지)
        boolean alreadyScrapped = favoriteDAO.findMemberScrap(memberId, bookIsbn) > 0;

        if(!alreadyScrapped){
            favoriteDAO.setBookScrap(memberId, bookIsbn);
        } else{
            log.info("이미 스크랩된 책");
        }
    };

    // 특정 책의 스크랩 수 조회
    public int getScrapCount(Long isbn){
        int scrapCount = favoriteDAO.findThisScrapCount(isbn);
        return scrapCount;
    };

    // 책 스크랩 취소
    public void deleteScrap(Long isbn){
        PersonalMemberDTO member = (PersonalMemberDTO) session.getAttribute("member");
        Long memberId = member.getId(); // 세션에서 아이디 받아옴

        favoriteDAO.deleteBookScrap(memberId, isbn);
    };

    //    독후감 좋아요하기
    public void setBookPostLike(Long memberId, Long bookPostId){};

    //    특정 독후감의 내 좋아요 여부 조회
    public int isBookPostLiked(Long memberId, Long bookPostId){
        return 0;
    };

    //    독후감 좋아요 취소하기
    public void deleteBookPostLike(Long memberId, Long bookPostId){};

    //    특정 회원 팔로우하기
    public void setMemberFollow(Long receiverId, Long senderId){};

    //    내 특정 회원 팔로우여부 조회
    public int isMemberFollowed(Long receiverId, Long senderId){
        return 0;
    };

    //    팔로우 취소
    public void deleteMemberFollow(Long receiverId, Long senderId){

    };
}
