package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.PostType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class SelectedBookPostVO extends BookPostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookId;
    private BookPostStatus bookPostStatus;


    public SelectedBookPostVO(BookPostVOBuilder<?, ?> b, Long id, Long bookId, BookPostStatus bookPostStatus) {
        super(b);
        this.id = id;
        this.bookId = bookId;
        this.bookPostStatus = bookPostStatus;
    }
}
