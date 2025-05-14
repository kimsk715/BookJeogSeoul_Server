package com.app.bookJeog.domain.dto;


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

public class CategoryBookDTO {
    private String isbn13;
    private String bookName;
    private String author;
    private String publisher;
    private String kdc;
    private String bookImage;
    private String className;
}
