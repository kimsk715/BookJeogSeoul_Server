package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.UnauthenticatedException;
import com.app.bookJeog.domain.dto.MemberDTO;
import com.app.bookJeog.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    // 특정 회원의 스크랩 여부 조회
    @GetMapping("/book/scrap-check")
    @ResponseBody
    public boolean isScrapped(@RequestParam Long isbn){
        return favoriteService.selectMemberScrap(isbn);
    }

    // 특정 책의 스크랩 수 조회
    @GetMapping("/book/scrap-count")
    @ResponseBody
    public int getScrapCount(@RequestParam Long isbn) {
        return favoriteService.getScrapCount(isbn);
    }

    // 스크랩 추가
    @PostMapping("/book/scrap-add")
    @ResponseBody
    public void scrap(@RequestParam Long isbn, HttpSession session) {
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        if(member == null){
//            throw new UnauthenticatedException("로그인이 필요한 서비스입니다.");
//        }
        favoriteService.scrap(isbn);
    }

    // 스크랩 삭제
    @DeleteMapping("/book/scrap-delete")
    @ResponseBody
    public void scrapDelete(@RequestParam Long isbn) {
        favoriteService.deleteScrap(isbn);
    }
}
