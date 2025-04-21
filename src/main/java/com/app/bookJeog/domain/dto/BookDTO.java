package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.BookPostVoteVO;
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
    private String bookIntro;
    private String bookIntroUrl;
    private String bookSummary;
    private String bookSummaryUrl;

    public BookVO toVO() {
        return BookVO.builder()
                .id(id)
                .bookIsbn(bookIsbn)
                .bookIntro(bookIntro)
                .bookIntroUrl(bookIntroUrl)
                .bookSummary(bookSummary)
                .bookSummaryUrl(bookSummaryUrl)
                .build();
    }


}
