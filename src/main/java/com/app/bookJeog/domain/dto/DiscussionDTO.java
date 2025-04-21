package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
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
public class DiscussionDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String discussionTitle;
    private String discussionText;
    private Long bookIsbn;

    public DiscussionVO toVO() {
        return DiscussionVO.builder()
                .id(id)
                .discussionTitle(discussionTitle)
                .discussionText(discussionText)
                .bookIsbn(bookIsbn)
                .build();
    }


}
