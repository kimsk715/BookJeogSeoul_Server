package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommentVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String commentText;
    private Long postId;
    private Long memberId;



    @Builder
    public CommentVO(String createdDate, String updatedDate, String commentText, Long id, Long memberId, Long postId) {
        super(createdDate, updatedDate);
        this.commentText = commentText;
        this.id = id;
        this.memberId = memberId;
        this.postId = postId;
    }
}
