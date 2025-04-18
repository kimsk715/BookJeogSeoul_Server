package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiverLikeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long receiverId;
    private Long memberId;
    private int receiverLikePoint;


    @Builder
    public ReceiverLikeVO(String createdDate, String updatedDate, Long id, Long memberId, Long receiverId, int receiverLikePoint) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.receiverId = receiverId;
        this.receiverLikePoint = receiverLikePoint;
    }
}
