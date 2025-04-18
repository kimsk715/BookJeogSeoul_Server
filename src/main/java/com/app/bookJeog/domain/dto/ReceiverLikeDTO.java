package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.ReceiverFileVO;
import com.app.bookJeog.domain.vo.ReceiverLikeVO;
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
public class ReceiverLikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long receiverId;
    private Long memberId;
    private int receiverLikePoint;

    public ReceiverLikeVO toVO() {
        return ReceiverLikeVO.builder()
                .id(id)
                .receiverId(receiverId)
                .memberId(memberId)
                .receiverLikePoint(receiverLikePoint)
                .build();
    }


}
