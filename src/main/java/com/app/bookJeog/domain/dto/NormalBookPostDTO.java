package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.FollowAlarmVO;
import com.app.bookJeog.domain.vo.NormalBookPostVO;
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
public class NormalBookPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookId;

    public NormalBookPostVO toVO() {
        return NormalBookPostVO.builder()
                .id(id)
                .bookId(bookId)
                .build();
    }


}
