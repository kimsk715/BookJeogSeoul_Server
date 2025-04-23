package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.PostType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;


@Component
@ToString
@Getter
@NoArgsConstructor
public class MonthlyBookPostVO  {
    private Long id;
    private Long bookPostId;
    private int bookPostLikeCount;
    private int bookPostVoteCount;
    private String bookPostTitle;
    private String bookPostText;
    private String createdDate;
    private String updatedDate;

    @Builder
    public MonthlyBookPostVO(Long id, Long bookPostId, int bookPostLikeCount, int bookPostVoteCount, String bookPostTitle, String bookPostText, String createdDate, String updatedDate) {
        this.id = id;
        this.bookPostId = bookPostId;
        this.bookPostLikeCount = bookPostLikeCount;
        this.bookPostVoteCount = bookPostVoteCount;
        this.bookPostTitle = bookPostTitle;
        this.bookPostText = bookPostText;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
