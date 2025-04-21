package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.enumeration.PostType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class BookPostVO extends PostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookPostTitle;
    private String bookPostText;
    private BookPostIsPublic bookPostIsPublic;
    private String bookPostStartDate;
    private String bookPostEndDate;
    private Long bookIsbn;

    public BookPostVO(PostVOBuilder<?, ?> b, Long id, String bookPostTitle, String bookPostText, BookPostIsPublic bookPostIsPublic, String bookPostStartDate, String bookPostEndDate, Long bookIsbn) {
        super(b);
        this.id = id;
        this.bookPostTitle = bookPostTitle;
        this.bookPostText = bookPostText;
        this.bookPostIsPublic = bookPostIsPublic;
        this.bookPostStartDate = bookPostStartDate;
        this.bookPostEndDate = bookPostEndDate;
        this.bookIsbn = bookIsbn;
    }
}
