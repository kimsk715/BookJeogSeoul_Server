package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/* 임시 선정 도서 VO */
public class TempSelectedBookVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookIsbn;



    @Builder
    public TempSelectedBookVO(String createdDate, String updatedDate, Long bookIsbn, Long id) {
        super(createdDate, updatedDate);
        this.bookIsbn = bookIsbn;
        this.id = id;
    }
}
