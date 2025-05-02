package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.SponsorMemberStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookIsbn;
//    private String bookIntro;
//    private String bookIntroUrl;
    private String bookSummary;
//    private String bookSummaryUrl;
}
