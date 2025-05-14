package com.app.bookJeog.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrailleBookDTO {
    @JsonProperty("도서명")
    private String bookTitle;

    @JsonProperty("저자")
    private String author;

    @JsonProperty("출판사")
    private String publisher;
}
