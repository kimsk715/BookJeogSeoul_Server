package com.app.bookJeog.service;

import org.springframework.ui.Model;

import java.util.Map;

public interface BookService {
    // 책 상세정보 API 호출
    String getBookDetail(String isbn);

    // 책 상세정보를 모델에 추가
    void parseAndAddBookInfoToModel(String response, Model model);
}
