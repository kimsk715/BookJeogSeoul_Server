package com.app.bookJeog.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor

public class OpenAPIResult {
    private Long isbn;          // 출처인 책.
    private String bookTitle;   // 책 제목
    private String topic;       // 토론 주제
    private String description; // 부연 설명

     @Builder
        public OpenAPIResult(Long isbn,String bookTitle, String topic,String description ) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.topic = topic;
        this.description = description;
     }

}