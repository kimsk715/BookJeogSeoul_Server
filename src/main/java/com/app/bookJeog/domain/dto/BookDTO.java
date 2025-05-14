package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.BookVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookIsbn;
    private String bookSummary;
    private String bookTitle;
    private String bookImage;
    private String bookAuthor;
    private String publisher;

    public BookVO toVO() {
        return BookVO.builder()
                .id(id)
                .bookIsbn(bookIsbn)
                .bookSummary(bookSummary)
                .build();
    }
}
