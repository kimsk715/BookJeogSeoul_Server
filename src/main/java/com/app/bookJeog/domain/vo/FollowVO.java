package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FollowVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long followReceiverId;
    private Long followSenderId;

    @Builder
    public FollowVO(String createdDate, String updatedDate, Long followReceiverId, Long followSenderId, Long id) {
        super(createdDate, updatedDate);
        this.followReceiverId = followReceiverId;
        this.followSenderId = followSenderId;
        this.id = id;
    }
}
