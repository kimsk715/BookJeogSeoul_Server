package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.service.AladinService;
import com.app.bookJeog.service.AlarmService;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class CategoryController {

    private final BookService bookService;
    private final AladinService aladinService;

    // kdc 로 카테고리별로 책 검색
    @GetMapping("/category/book/{kdc}")
    public String selectBookByKDC (@PathVariable Long kdc, Model model) {
        List<CategoryBookDTO> bookList = bookService.selectBooksByKdc(kdc);
        log.info("bookList size: {}", bookList.size());
        bookList.forEach((book) -> {
            book.setBookImage(aladinService.getBookCover(Long.valueOf(book.getIsbn13())));
        });
        log.info("bookList size: {}", bookList.size());

        // 첫 번째 책의 className만 추출
        String className = bookList.isEmpty() ? "" : bookList.get(0).getClassName().split(">")[0];
        log.info("className : {}", className);


        model.addAttribute("bookList", bookList);
        model.addAttribute("className", className);


        return "category/category-overview";
    }
}
