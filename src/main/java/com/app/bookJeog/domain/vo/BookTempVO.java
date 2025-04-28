package com.app.bookJeog.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// 서울도서관 책 정보 VO 여기는 절대 건들지 말아야 함.
@Data
public class BookTempVO {

    @JsonProperty("TITLE")
    private String title;


    @JsonProperty("AUTHOR")
    private String author;

    @JsonProperty("CONTROLNO")
    private String controlNo;

    @JsonProperty("PUBLISHER")
    private String publisher;



    @JsonProperty("CLASS_NO")
    private String classNo;

    @JsonProperty("PUBLISHER_YEAR")
    private String publishedYear;

    @JsonProperty("CNT")
    private String cnt;


    @JsonProperty("ISBN")
    private String isbn;






}
