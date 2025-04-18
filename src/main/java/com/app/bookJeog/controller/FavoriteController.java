package com.app.bookJeog.controller;

import com.app.bookJeog.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    // 특정 책의 스크랩 수 조회
    @GetMapping("/book/scrap-count")
    @ResponseBody
    public int getScrapCount(@RequestParam String isbn) {
        return favoriteService.getScrapCount(isbn);
    }

    // 스크랩 추가
    @PostMapping("/book/scrap-add")
    @ResponseBody
    public void scrap(@RequestParam String isbn) {
        favoriteService.scrap(isbn);
    }

    // 스크랩 삭제
    @DeleteMapping("/book/scrap-delete")
    @ResponseBody
    public void scrapDelete(@RequestParam String isbn) {
        favoriteService.deleteScrap(isbn);
    }
}
