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
public class BookPostVO extends PostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookPostTitle;
    private String bookPostText;
    private BookPostIsPublic bookPostIsPublic;
    private String bookPostStartDate;
    private String bookPostEndDate;



    public BookPostVO(String createdDate, String updatedDate, Long id, Long memberId, PostType postType, String bookPostEndDate, BookPostIsPublic bookPostIsPublic, String bookPostStartDate, String bookPostText, String bookPostTitle, Long id1) {
        super(createdDate, updatedDate, id, memberId, postType);
        this.bookPostEndDate = bookPostEndDate;
        this.bookPostIsPublic = bookPostIsPublic;
        this.bookPostStartDate = bookPostStartDate;
        this.bookPostText = bookPostText;
        this.bookPostTitle = bookPostTitle;
        this.id = id1;
    }
}
