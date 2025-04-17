package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.PostType;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookPostVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookPostTitle;
    private String bookPostText;
    private BookPostIsPublic bookPostIsPublic;
    private String bookPostStartDate;
    private String bookPostEndDate;


    @Builder
    public BookPostVO(String createdDate, String updatedDate, Long id, String bookPostTitle, String bookPostText, String bookPostStartDate, BookPostIsPublic bookPostIsPublic, String bookPostEndDate) {
        super(createdDate, updatedDate);
        this.id = id;
        this.bookPostTitle = bookPostTitle;
        this.bookPostText = bookPostText;
        this.bookPostStartDate = bookPostStartDate;
        this.bookPostIsPublic = bookPostIsPublic;
        this.bookPostEndDate = bookPostEndDate;
    }


}
