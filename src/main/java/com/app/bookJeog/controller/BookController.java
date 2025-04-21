package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        } else {
            throw new ResourceNotFoundException(isbn);
        }

        // 디버그 로그로 확인
        log.info("Model contains 'title': " + model.getAttribute("title"));

        return "book/book-detail";
    }

    // 이 작가의 다른 도서 목록
    @GetMapping("detail/author-books")
    @ResponseBody
    public ResponseEntity<String> getAuthorBooks(@RequestParam("author") String encodedAuthor) {
        String json = bookService.getBooksByAuthor(encodedAuthor);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(json);
    }

    // 이 책의 모든 독후감 목록
    @GetMapping("post-list/{isbn}")
    public String gotoPostList(@PathVariable Long isbn) {
        return "book/book-detail-post"; // HTML 페이지 반환
    }
}