package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.PostType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class SelectedBookVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookId;
    private BookPostStatus bookPostStatus;


    public SelectedBookVO(String createdDate, String updatedDate, Long bookId, BookPostStatus bookPostStatus, Long id) {
        super(createdDate, updatedDate);
        this.bookId = bookId;
        this.bookPostStatus = bookPostStatus;
        this.id = id;
    }
}
