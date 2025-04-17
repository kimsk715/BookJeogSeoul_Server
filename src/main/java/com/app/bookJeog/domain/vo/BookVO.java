package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.SponsorMemberStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookIsbn;
    private String bookIntro;
    private String bookIntroUrl;
    private String bookSummary;
    private String bookSummaryUrl;


    @Builder
    public BookVO(String createdDate, String updatedDate, String bookIntro, String bookIntroUrl, String bookIsbn, String bookSummary, String bookSummaryUrl, Long id) {
        super(createdDate, updatedDate);
        this.bookIntro = bookIntro;
        this.bookIntroUrl = bookIntroUrl;
        this.bookIsbn = bookIsbn;
        this.bookSummary = bookSummary;
        this.bookSummaryUrl = bookSummaryUrl;
        this.id = id;
    }
}
