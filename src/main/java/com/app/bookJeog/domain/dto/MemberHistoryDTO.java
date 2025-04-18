package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.CommentAlarmVO;
import com.app.bookJeog.domain.vo.MemberHistoryVO;
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
public class MemberHistoryDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;

    public MemberHistoryVO toVO() {
        return MemberHistoryVO.builder()
                .id(id)
                .memberId(memberId)
                .bookIsbn(bookIsbn)
                .build();

    }

}
