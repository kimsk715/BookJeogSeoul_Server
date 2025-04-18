package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.vo.BookPostVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String bookPostTitle;
    private String bookPostText;
    private BookPostIsPublic bookPostIsPublic;
    private String bookPostStartDate;
    private String bookPostEndDate;
    private Long bookIsbn;
    private String createdDate;
    private String updatedDate;
    private Long memberId;
    private String memberNickName;
    private String filePath;
    private String fileName;

    public BookPostVO toVO() {
        return BookPostVO.builder()
                .id(id)
                .bookPostTitle(bookPostTitle)
                .bookPostText(bookPostText)
                .bookPostIsPublic(bookPostIsPublic)
                .bookPostStartDate(bookPostStartDate)
                .bookPostEndDate(bookPostEndDate)
                .bookIsbn(bookIsbn)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
