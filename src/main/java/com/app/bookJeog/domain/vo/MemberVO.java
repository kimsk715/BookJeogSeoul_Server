package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberType;
import lombok.*;


@Getter @ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MemberVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private MemberType memberType;

    @Builder
    public MemberVO(String createdDate, String updatedDate, Long id, MemberType memberType) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberType = memberType;
    }
}
