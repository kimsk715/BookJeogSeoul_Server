package com.app.bookJeog.repository;

import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDAO {
    private final BookMapper bookMapper;

    public void insertTempSelectedBook(Long isbn) {
        bookMapper.insertTempSelectedBook(isbn);
    }

    public List<TempSelectedBookVO> findTempSelectedBook() {
        return bookMapper.selectTempSelectedBook();
    }

    public void insertSelectedBook(SelectedBookVO SelectedBookVO) {
        bookMapper.insertSelectedBook(SelectedBookVO);
    }


    // 인기도서 최다조회
    public List<MemberHistoryVO> findTopViewBook () {
        return bookMapper.selectTopViewBook();
    }


    // 관리자 추천도서
    public List<SelectedBookVO> findAdminSuggestBook() {
        return bookMapper.selectAdminSuggestBooks();
    }


    // 인기 독후감
    public List<BookPostVO> findTopBookPost(){
        return bookMapper.selectTopBookPost();
    }
}
