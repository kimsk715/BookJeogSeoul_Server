package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookReceivedStatus;
import com.app.bookJeog.domain.vo.BookDonateVO;
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
public class BookDonateDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;
    private BookReceivedStatus bookReceivedStatus;
    private String bookTitle;
    private String createdDate;
    private String updatedDate;

    public BookDonateVO toVO() {
        return BookDonateVO.builder()
                .id(id)
                .memberId(memberId)
                .bookIsbn(bookIsbn)
                .bookReceivedStatus(bookReceivedStatus)
                .bookTitle(bookTitle)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }

}
