package com.app.bookJeog.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberProfileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;


    public MemberProfileVO(Long id, Long memberId) {
        this.id = id;
        this.memberId = memberId;
    }
}
