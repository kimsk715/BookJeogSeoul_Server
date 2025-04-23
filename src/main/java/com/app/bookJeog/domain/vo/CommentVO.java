package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.CommentStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
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
    private CommentStatus commentStatus;
}
