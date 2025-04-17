package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NormalBookPostVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookId;


    @Builder
    public NormalBookPostVO(String createdDate, String updatedDate, Long bookId, BookPostStatus bookPostStatus, Long id) {
        super(createdDate, updatedDate);
        this.bookId = bookId;
        this.id = id;
    }
}
