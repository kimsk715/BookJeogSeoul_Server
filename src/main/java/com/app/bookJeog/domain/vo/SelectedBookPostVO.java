package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.PostType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.awt.print.Book;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SelectedBookPostVO extends BookPostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookId;
    private BookPostStatus bookPostStatus;



    @Builder
    public SelectedBookPostVO(String createdDate, String updatedDate, Long id, Long memberId, PostType postType, String bookPostEndDate, BookPostIsPublic bookPostIsPublic, String bookPostStartDate, String bookPostText, String bookPostTitle, Long id1, Long bookId, BookPostStatus bookPostStatus, Long id2) {
        super(createdDate, updatedDate, id, memberId, postType, bookPostEndDate, bookPostIsPublic, bookPostStartDate, bookPostText, bookPostTitle, id1);
        this.bookId = bookId;
        this.bookPostStatus = bookPostStatus;
        this.id = id2;
    }
}
