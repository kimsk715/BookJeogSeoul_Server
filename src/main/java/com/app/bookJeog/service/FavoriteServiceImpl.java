package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.MemberDTO;
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
//        MemberDTO member = (MemberDTO)session.getAttribute("member");
//        Long memberId = member.getId();
        Long memberId = 1L;
//        if (member == null) return false; // 로그인 안 된 경우 false

        return favoriteDAO.findMemberScrap(memberId, bookIsbn) > 0;
    };

    // 책 스크랩하기 버튼
    public void scrap(Long bookIsbn){
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        Long memberId = member.getId(); // 세션에서 아이디 받아옴
        Long memberId = 1L;


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
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        Long memberId = member.getId(); // 세션에서 아이디 받아옴
        Long memberId = 1L;

        favoriteDAO.deleteBookScrap(memberId, isbn);
    };
}
