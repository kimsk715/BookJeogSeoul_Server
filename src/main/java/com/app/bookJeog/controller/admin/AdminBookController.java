package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.BookInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.TempSelectedBookDTO;
import com.app.bookJeog.domain.vo.BookInfoVO;
import com.app.bookJeog.domain.vo.BookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import com.app.bookJeog.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public BookInfoDTO getAllBook(@RequestParam(value = "type", required = false) String type, @RequestParam("page") int page, @RequestParam(value = "keyword", required = false) String keyword ,HttpServletRequest request, Pagination pagination) {
        pagination.setPage(page);
        BookInfoDTO Books = bookService.getAllBook(pagination, keyword, type);
        log.info("{}",request.getAttribute("test"));
        return Books;
    }

    @GetMapping("admin/temp-list")
    @ResponseBody
    public void getTempList(@RequestParam("list") List<String> tempList) {
        log.info(tempList.toString());
        tempList.forEach(book -> {bookService.insertTempSelectedBook(Long.valueOf(book));});
    }

    @GetMapping("admin/temp-lists")
    @ResponseBody
    public List<TempSelectedBookDTO> getTempLists() {
        List<TempSelectedBookVO> tempList = bookService.getTempSelectedBook();
        List<TempSelectedBookDTO> newList = tempList.stream().map(bookService::toTempSelectedBookDTO).toList();
        newList.forEach( book -> book.setTitle(bookService.getBookByIsbn(book.getBookIsbn()).get(0).getTitle()));
        newList.forEach( book -> book.setClassNo(bookService.getBookByIsbn(book.getBookIsbn()).get(0).getClassNo()));
        return newList;
    }
}
