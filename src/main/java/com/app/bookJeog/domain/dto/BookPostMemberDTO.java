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
public class BookPostMemberDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookPostTitle;
    private String bookPostText;
    private BookPostIsPublic bookPostIsPublic;
    private String bookPostStartDate;
    private String bookPostEndDate;
    private Long bookIsbn;
    private String bookTitle;
    private String createdDate;
    private String updatedDate;
    private Long memberId;
    private String memberNickName;
    private String filePath;
    private String fileName;
    private String bookCover;
    private int count;
    private int likeCount;

    public BookPostVO toVO() {
        return BookPostVO.builder()
                .id(id)
                .bookPostTitle(bookPostTitle)
                .bookPostText(bookPostText)
                .bookPostIsPublic(bookPostIsPublic)
                .bookPostStartDate(bookPostStartDate)
                .bookPostEndDate(bookPostEndDate)
                .bookIsbn(bookIsbn)
                .bookTitle(bookTitle)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();

    }
}
