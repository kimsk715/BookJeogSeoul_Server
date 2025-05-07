package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.PostType;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.domain.vo.SelectedBookPostVO;
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
    private Long bookIsbn;
    private Long bookId;
    private int likeCount;
    private PostType postType;

    private String bookPostTitle;
    private String bookPostText;

    private BookPostIsPublic bookPostIsPublic;
    private BookPostStatus bookPostStatus;

    private String memberName;
    private String memberNickName;
    private Long memberId;
    private String filePath;
    private String fileName;

    private String bookPostStartDate;
    private String bookPostEndDate;
    private String createdDate;

    private List<BookPostFileDTO> fileList; // 독후감 첨부파일

    public PostVO toPostVO() {
        return PostVO.builder()
                .id(bookPostId)
                .postType(postType)
                .memberId(memberId)
                .createdDate(createdDate)
                .build();
    }

    public BookPostVO toBookPostVO() {
        return BookPostVO.builder()
                .id(bookPostId)
                .bookPostTitle(bookPostTitle)
                .bookPostText(bookPostText)
                .bookPostIsPublic(bookPostIsPublic)
                .bookPostStartDate(bookPostStartDate)
                .bookPostEndDate(bookPostEndDate)
                .bookIsbn(bookIsbn)
                .bookTitle(bookTitle)
                .build();
    }

    public SelectedBookPostVO toSelectedBookPostVO() {
        return SelectedBookPostVO.builder()
                .id(bookPostId)
                .bookId(bookId)
                .bookPostStatus(bookPostStatus)
                .build();
    }
}
