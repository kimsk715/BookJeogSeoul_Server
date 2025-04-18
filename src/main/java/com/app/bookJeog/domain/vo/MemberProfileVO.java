package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberProfileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;

    @Builder
    public MemberProfileVO(Long id, Long memberId) {
        this.id = id;
        this.memberId = memberId;
    }
}
