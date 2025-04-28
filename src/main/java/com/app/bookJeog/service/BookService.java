package com.app.bookJeog.service;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

public interface BookService {

//    서울도서관 소장자료 현황 API 로부터 데이터 요청하는 메소드
//    초기 요청은 페이지 1에 전체 조회이고, 10개만 보임.
//    페이지 버튼 클릭 시 해당 페이지로 이동
    public default List<BookInfoVO> getBookByIsbn(Long bookIsbn) {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/").append(URLEncoder.encode("5a51544c6d6b696d3739455a645457", "UTF-8")); // 인증키
            urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 타입
            urlBuilder.append("/").append(URLEncoder.encode("SeoulLibraryBookSearchInfo", "UTF-8")); // 서비스명
            urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 시작위치
            urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 종료위치
            urlBuilder.append("/%20").append("/%20").append("/%20").append("/").append(URLEncoder.encode(String.valueOf(bookIsbn), "UTF-8"));
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
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(response.toString());
            JsonNode totalCounts = node.path("SeoulLibraryBookSearchInfo").path("list_total_count");
            JsonNode rowNode = node.path("SeoulLibraryBookSearchInfo").path("row");
            List<BookInfoVO> foundBook = objectMapper.readValue(rowNode.toString(), new TypeReference<List<BookInfoVO>>() {});
            return foundBook;
    }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public default BookInfoDTO getAllBook(Pagination pagination, String keyword, String type) {
        BookInfoDTO bookInfoDTO = new BookInfoDTO();
        List<BookInfoVO> allBooks = new ArrayList<>();
        System.out.println(type);
        System.out.println(keyword);
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/").append(URLEncoder.encode("5a51544c6d6b696d3739455a645457", "UTF-8")); // 인증키
            urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 타입
            urlBuilder.append("/").append(URLEncoder.encode("SeoulLibraryBookSearchInfo", "UTF-8")); // 서비스명
            urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 시작위치
            urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 종료위치
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
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(response.toString());
            JsonNode totalCounts = node.path("SeoulLibraryBookSearchInfo").path("list_total_count");
            JsonNode rowNode;
// 여기까지는 일단 초기화면에서의 전체 카운트를 가져오기 위한 요청.

                pagination.create(totalCounts.asInt());
                bookInfoDTO.setPagination(pagination);
// 기본 페이지네이션 생성
                int newStart = (pagination.getPage()-1) * pagination.getRowCount() + 1;
                int newEnd = newStart -1 + pagination.getRowCount();
// 기본 페이지네이션을 바탕으로 1페이지를 호출.
                urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
                urlBuilder.append("/").append(URLEncoder.encode("5a51544c6d6b696d3739455a645457", "UTF-8")); // 인증키
                urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 타입
                urlBuilder.append("/").append(URLEncoder.encode("SeoulLibraryBookSearchInfo", "UTF-8")); // 서비스명

//            만약에 키워드가 있는 경우.
            if(keyword != null) {
//                일단 검색 결과의 개수를 가져오기 위한 API 요청
                urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 시작위치
                urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 종료위치
                switch (type){
//                    키워드의 타입(ISBN, 작가명, 도서명)에 따라 다르게 주소를 요청
                    case "title":
                        urlBuilder.append("/").append(URLEncoder.encode(keyword, "UTF-8"));
                        break;
                    case "author":
                        urlBuilder.append("/%20").append("/%20").append(URLEncoder.encode(keyword, "UTF-8"));
                        break;
                    case "isbn":
                        urlBuilder.append("/%20").append("/%20").append("/%20").append("/").append(URLEncoder.encode(keyword, "UTF-8"));
                        break;

                }
                url = new URL(urlBuilder.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");

                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                }
                rd.close();
                conn.disconnect();
                objectMapper = new ObjectMapper();
                node = objectMapper.readTree(response.toString());
                JsonNode totalSearchedCount = node.path("SeoulLibraryBookSearchInfo").path("list_total_count");
                pagination.create(totalSearchedCount.asInt());
                bookInfoDTO.setPagination(pagination);
//              여기까지 해서 키워드가 있을 때의 페이지네이션 생성.
//              이제 실제로 화면에 띄우기 위해 API 에서 요청.
                urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
                urlBuilder.append("/").append(URLEncoder.encode("5a51544c6d6b696d3739455a645457", "UTF-8")); // 인증키
                urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 타입
                urlBuilder.append("/").append(URLEncoder.encode("SeoulLibraryBookSearchInfo", "UTF-8")); // 서비스명
                urlBuilder.append("/").append(URLEncoder.encode(String.valueOf(newStart), "UTF-8")); // 시작위치
                urlBuilder.append("/").append(URLEncoder.encode(String.valueOf(newEnd), "UTF-8")); // 종료위치
                switch (type){
                    case "title":
                        urlBuilder.append("/").append(URLEncoder.encode(keyword, "UTF-8"));
                        break;
                    case "author":
                        urlBuilder.append("/%20").append("/").append(URLEncoder.encode(keyword, "UTF-8"));
                        break;
                    case "isbn":
                        urlBuilder.append("/%20").append("/%20").append("/%20").append("/").append(URLEncoder.encode(keyword, "UTF-8"));
                        break;
                }

                url = new URL(urlBuilder.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");

                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                }
                rd.close();
                conn.disconnect();

                objectMapper = new ObjectMapper();
                node = objectMapper.readTree(response.toString());
                rowNode = node.path("SeoulLibraryBookSearchInfo").path("row");
                List<BookInfoVO> books = objectMapper.readValue(rowNode.toString(), new TypeReference<List<BookInfoVO>>() {});
                bookInfoDTO.setBookInfoList(books);
                return bookInfoDTO;
            }
//           여기는 키워드 검색이 없는 경우 그냥 페이지네이션에 따라 호출.
            else{
            urlBuilder.append("/").append(URLEncoder.encode(String.valueOf(newStart), "UTF-8")); // 시작위치
            urlBuilder.append("/").append(URLEncoder.encode(String.valueOf(newEnd), "UTF-8")); // 종료위치
                url = new URL(urlBuilder.toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");

                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                }
                rd.close();
                conn.disconnect();

                objectMapper = new ObjectMapper();
                node = objectMapper.readTree(response.toString());
                rowNode = node.path("SeoulLibraryBookSearchInfo").path("row");
                List<BookInfoVO> books = objectMapper.readValue(rowNode.toString(), new TypeReference<List<BookInfoVO>>() {});

                bookInfoDTO.setBookInfoList(books);
                return bookInfoDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public default TempSelectedBookDTO toTempSelectedBookDTO(TempSelectedBookVO tempSelectedBookVO){
        TempSelectedBookDTO tempSelectedBookDTO = new TempSelectedBookDTO();
        if(tempSelectedBookVO != null){
            tempSelectedBookDTO.setId(tempSelectedBookVO.getId());
            tempSelectedBookDTO.setBookIsbn(tempSelectedBookVO.getBookIsbn());
        }
        return tempSelectedBookDTO;
    }

    public void insertTempSelectedBook(Long isbn);

    public List<TempSelectedBookVO> getTempSelectedBook();

    public void insertSelectedBook(SelectedBookVO selectedBookVO);
//    api 요청 코드를 default 메소드로 인터페이스에 분리해서 구현
//    ex) 신착도서 정보 요청 코드를 bookservice 에 넣기

    // 책 상세정보 API 호출
    String getBookDetail(String isbn);

    // 책 상세정보를 모델에 추가
    void parseAndAddBookInfoToModel(String response, Model model);

    // 이 작가의 다른 책
    String getBooksByAuthor(String authorName);

    // 서울 도서관 최다 대출
    public default List<TopBookVO> getPopularBooks() throws IOException {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/").append(URLEncoder.encode("5a51544c6d6b696d3739455a645457", "UTF-8")); // 인증키
            urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 타입
            urlBuilder.append("/").append(URLEncoder.encode("SeoulLibraryBookRentNumInfo", "UTF-8")); // 서비스명
            urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 시작위치
            urlBuilder.append("/").append(URLEncoder.encode("7", "UTF-8")); // 종료위치

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
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(response.toString());

            JsonNode rowNode = node.path("SeoulLibraryBookRentNumInfo").path("row");
            List<TopBookVO> foundBooks = objectMapper.readValue(rowNode.toString(), new TypeReference<List<TopBookVO>>() {});

            return foundBooks;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 서울 새로들어오는 도서 목록조회
    public default List<NewBookDTO> getNewBooks() throws IOException {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/").append(URLEncoder.encode("5a51544c6d6b696d3739455a645457", "UTF-8")); // 인증키
            urlBuilder.append("/").append(URLEncoder.encode("json", "UTF-8")); // 타입
            urlBuilder.append("/").append(URLEncoder.encode("SeoulLibNewArrivalInfo", "UTF-8")); // 서비스명
            urlBuilder.append("/").append(URLEncoder.encode("1", "UTF-8")); // 시작위치
            urlBuilder.append("/").append(URLEncoder.encode("20", "UTF-8")); // 종료위치

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
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(response.toString());

            JsonNode rowNode = node.path("SeoulLibNewArrivalInfo").path("row");
            List<NewBookDTO> foundNewBooks = objectMapper.readValue(rowNode.toString(), new TypeReference<List<NewBookDTO>>() {});

            return foundNewBooks;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // 인기도서 최다조회
    public List<MemberHistoryVO> selectTopViewBooks();
    // 관리자 추천도서
    public List<SelectedBookVO> selectAdminSuggestBooks();
}
