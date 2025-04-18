package com.app.bookJeog.service;

import com.app.bookJeog.domain.vo.BookInfoVO;
import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import com.app.bookJeog.mapper.BookMapper;
import com.app.bookJeog.repository.BookDAO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;
    @Override
    public void insertTempSelectedBook(Long isbn) {
        bookDAO.insertTempSelectedBook(isbn);
    }

    @Override
    public List<TempSelectedBookVO> getTempSelectedBook() {
        return bookDAO.findTempSelectedBook();
    }

    @Override
    public void insertSelectedBook(SelectedBookVO selectedBookVO) {
        bookDAO.insertSelectedBook(selectedBookVO);
    }




}
