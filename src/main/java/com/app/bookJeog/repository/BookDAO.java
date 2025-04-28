package com.app.bookJeog.repository;

import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import com.app.bookJeog.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
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

    // 선정 도서 여부 조회
    public int findSelectedBooks(Long bookIsbn){ return bookMapper.selectSelectedBooks(bookIsbn); };
}
