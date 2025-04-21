package com.app.bookJeog.domain.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class ReceiverLikeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long receiverId;
    private Long memberId;
    private int receiverLikePoint;


    public ReceiverLikeVO(String createdDate, String updatedDate, Long id, Long memberId, Long receiverId, int receiverLikePoint) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.receiverId = receiverId;
        this.receiverLikePoint = receiverLikePoint;
    }
}
