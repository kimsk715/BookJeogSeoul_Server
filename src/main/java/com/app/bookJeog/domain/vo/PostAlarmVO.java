package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostAlarmVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;

    @Builder
    public PostAlarmVO(String createdDate, String updatedDate, Long commentId, Long id) {
        super(createdDate, updatedDate);
        this.commentId = commentId;
        this.id = id;
    }
}
