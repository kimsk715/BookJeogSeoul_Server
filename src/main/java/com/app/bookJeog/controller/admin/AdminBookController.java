package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.vo.BookInfoVO;
import com.app.bookJeog.domain.vo.BookVO;
import com.app.bookJeog.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
// 도서 목록 및 도서 선정 관련 기능
public class AdminBookController {
    private final BookService bookService;

    @GetMapping("admin/books")
    @ResponseBody
    public List<BookInfoVO> getAllBook() {
        List<BookInfoVO> Books = bookService.getAllBook();
        log.info(Books.toString());
        return Books;
    }


}
