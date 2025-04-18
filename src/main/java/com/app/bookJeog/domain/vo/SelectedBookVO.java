package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SelectedBookVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookIsbn;
    private String bookImageUrl;


    @Builder
    public SelectedBookVO(String createdDate, String updatedDate, Long bookIsbn, Long id, String bookImageUrl) {
        super(createdDate, updatedDate);
        this.bookIsbn = bookIsbn;
        this.id = id;
        this.bookImageUrl = bookImageUrl;
    }

}
