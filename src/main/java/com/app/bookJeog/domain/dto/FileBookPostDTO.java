package com.app.bookJeog.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FileBookPostDTO {
    @EqualsAndHashCode.Include
    private Long bookPostId;
    private String bookTitle;
    private String bookIsbn;
    private int likeCount;

    private String bookPostTitle;
    private String bookPostText;

    private String memberName;
    private String memberId;
    private String filePath;
    private String fileName;

    private String bookPostStartDate;
    private String bookPostEndDate;
    private String createdDate;

    private List<BookPostFileDTO> fileList; // 독후감 첨부파일
}
