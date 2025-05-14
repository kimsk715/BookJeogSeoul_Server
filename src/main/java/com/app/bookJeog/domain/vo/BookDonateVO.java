package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookReceivedStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SuperBuilder
public class BookDonateVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;
    private BookReceivedStatus bookReceivedStatus;
    private String bookTitle;
}
