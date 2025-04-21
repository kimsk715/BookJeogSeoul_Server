package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
public class MonthlyBookPostDTO {
    private Long id;
    private Long bookPostId;
    private String bookPostTitle;
    private int bookPostLikeCount;
    private int bookPostVoteCount;
    private String bookPostText;
    private String createdDate;
    private String updatedDate;

    public MonthlyBookPostVO toMonthlyBookPostVO() {
        return MonthlyBookPostVO.builder()
                .id(id)
                .bookPostId(bookPostId)
                .bookPostTitle(bookPostTitle)
                .bookPostLikeCount(bookPostLikeCount)
                .bookPostVoteCount(bookPostVoteCount)
                .bookPostText(bookPostText)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
