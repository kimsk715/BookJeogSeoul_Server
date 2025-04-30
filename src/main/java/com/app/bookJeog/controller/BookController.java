package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.ResourceNotFoundException;
import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.service.AladinService;
import com.app.bookJeog.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/book/*")
@RequiredArgsConstructor
public class BookController implements BookControllerDocs {
    private final BookService bookService;
    private final AladinService aladinService;


    // 도서 상세 페이지로 이동
    @GetMapping("detail/{isbn}")
    public String gotoBookDetail(@PathVariable String isbn, Model model) {
        String response = bookService.getBookDetail(isbn);

        if (response != null) {
            bookService.parseAndAddBookInfoToModel(response, model);
        } else {
            throw new ResourceNotFoundException(isbn);
        }

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

    // 커버 이미지 링크만 반환
    @GetMapping("cover")
    @ResponseBody
    public String getBookCover(@RequestParam Long isbn) {
        return aladinService.getBookCover(isbn);
    }

    // 알라딘 api로 단일 도서 정보 받기
    @GetMapping("/api/{isbn}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getBookInfo(@PathVariable Long isbn) {
        Map<String, Object> result = aladinService.getBookInfoAsMap(isbn);
        return ResponseEntity.ok(result);}


    // 최다 대출 도서
//    @GetMapping("popular")
//    public List<BookTempVO> getPopularBooks() throws IOException {
//        log.info(bookService.getPopularBooks().toString());
//        return bookService.getPopularBooks();
//    }

    // 검색어로 책 목록 띄우기
    @GetMapping("search")
    @ResponseBody
    public Map<String, Object> searchBooks(@RequestParam("keyword") String keyword,
                                        @RequestParam(value = "start", defaultValue = "1") int startIndex,
                                        @RequestParam(value = "max", defaultValue = "20") int maxResults,
                                        @RequestParam(value = "sort", defaultValue = "Accuracy") String sort) throws JSONException {
        Map<String, Object> result = aladinService.searchBooksToMap(keyword, startIndex, maxResults, sort);
        return result;
    }

    // isbn으로 선정도서 여부 조회
    @GetMapping("find-selected")
    @ResponseBody
    public Long findSelectedBooks(Long bookIsbn) {
        return bookService.findSelectedBooks(bookIsbn);
    }
}
