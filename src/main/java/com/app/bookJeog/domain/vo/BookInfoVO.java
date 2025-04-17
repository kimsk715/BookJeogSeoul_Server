package com.app.bookJeog.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter

@Data
public class BookInfoVO {

    @JsonProperty("CONTROLNO")
    private String controlNo;

    @JsonProperty("TITLE")
    private String title;

    @JsonProperty("AUTHOR")
    private String author;

    @JsonProperty("PUBLISHER")
    private String publisher;

    @JsonProperty("PUBLISHER_YEAR")
    private String publishedYear;

    @JsonProperty("ISBN")
    private String isbn;

    @JsonProperty("CLASS_NO")
    private String classNo;

    @JsonProperty("CNT")
    private String count;
}
