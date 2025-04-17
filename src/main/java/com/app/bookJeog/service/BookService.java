package com.app.bookJeog.service;
import com.app.bookJeog.domain.vo.BookInfoVO;
import com.app.bookJeog.domain.vo.BookVO;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface BookService {

    public List<BookInfoVO> getAllBook();
}
