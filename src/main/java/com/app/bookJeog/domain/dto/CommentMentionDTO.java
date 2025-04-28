package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.BookPostVoteVO;
import com.app.bookJeog.domain.vo.CommentMentionVO;
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
public class CommentMentionDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;
    private Long mentionMemberId;

    public CommentMentionVO toVO() {
        return CommentMentionVO.builder()
                .id(id)
                .commentId(commentId)
                .mentionMemberId(mentionMemberId)
                .build();
    }


}
