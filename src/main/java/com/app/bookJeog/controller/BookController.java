package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.controller.member.MemberControllerDocs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

@Controller
@Slf4j
@RequestMapping("/book/*")
@RequiredArgsConstructor
public class BookController implements BookControllerDocs {

    private final BookService bookService;


    // 도서 상세 페이지로 이동
    @GetMapping("detail/{isbn}")
    public String gotoBookDetail(@PathVariable String isbn, Model model) {
        String response = bookService.getBookDetail(isbn);

        if (response != null) {
            bookService.parseAndAddBookInfoToModel(response, model);
        } else{
            throw new ResourceNotFoundException(isbn);
        }

        // 디버그 로그로 확인
        log.info("Model contains 'title': " + model.getAttribute("title"));

        return "book/book-detail";
    }
}