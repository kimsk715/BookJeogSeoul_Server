package com.app.bookJeog.controller;

import com.app.bookJeog.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
