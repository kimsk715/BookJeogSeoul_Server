package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.BookInfoVO;
import com.app.bookJeog.domain.vo.TopBookVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class NewBookDTO {
    @JsonProperty("TITLE")
    private String title;
    @JsonProperty("ISBN")
    private String isbn;
    @JsonProperty("AUTHOR")
    private String author;
}
