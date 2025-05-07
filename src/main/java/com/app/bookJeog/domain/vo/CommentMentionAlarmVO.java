package com.app.bookJeog.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommentMentionAlarmVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;
    private Long mentionMemberId;


}
