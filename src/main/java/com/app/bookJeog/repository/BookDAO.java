package com.app.bookJeog.repository;

import com.app.bookJeog.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookDAO {
    private final BookMapper bookMapper;

    public void insertTempSelectedBook(Long isbn) {
        bookMapper.insertTempSelectedBook(isbn);
    }

}
