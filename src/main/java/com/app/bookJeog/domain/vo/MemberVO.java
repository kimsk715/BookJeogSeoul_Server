package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberType;
import lombok.*;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
