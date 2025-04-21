package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.vo.BookPostVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookPostTitle;
    private String bookPostText;
    private BookPostIsPublic bookPostIsPublic;
    private String bookPostStartDate;
    private String bookPostEndDate;
    private String createdDate;
    private String updatedDate;
    private Long bookId;
    private Long bookIsbn;

    public BookPostVO toVO(){
        return BookPostVO.builder().
                id(id).
                bookPostTitle(bookPostTitle).
                bookPostText(bookPostText).
                bookPostIsPublic(bookPostIsPublic).
                bookPostStartDate(bookPostStartDate).
                bookPostEndDate(bookPostEndDate).
                bookId(bookId).
                bookIsbn(bookIsbn).
                createdDate(createdDate).
                updatedDate(updatedDate).
                build();
    }

    
}
