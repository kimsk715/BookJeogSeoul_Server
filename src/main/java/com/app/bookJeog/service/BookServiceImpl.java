package com.app.bookJeog.service;

import com.app.bookJeog.domain.vo.BookInfoVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
//    http://openapi.seoul.go.kr:8088/(인증키)/xml/SeoulLibNewArrivalInfo/1/5/
@Override
public List<BookInfoVO> getAllBook() {
    try {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/").append(URLEncoder.encode("5a51544c6d6b696d3739455a645457", "UTF-8")); // 인증키
        urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 타입
        urlBuilder.append("/").append(URLEncoder.encode("SeoulLibraryBookRentNumInfo", "UTF-8")); // 서비스명
        urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 시작위치
        urlBuilder.append("/").append(URLEncoder.encode("10", "UTF-8")); // 종료위치

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println("API Response: " + response.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(response.toString());
        JsonNode rowNode = node.path("SeoulLibraryBookRentNumInfo").path("row");

        List<BookInfoVO> books = objectMapper.readValue(rowNode.toString(), new TypeReference<List<BookInfoVO>>() {});

        return books; // 임시 객체 반환
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}
