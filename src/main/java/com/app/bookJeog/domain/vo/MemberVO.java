package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberType;
import lombok.*;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class MemberVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private MemberType memberType;


    public MemberVO(PeriodBuilder<?, ?> b, Long id, MemberType memberType) {
        super(b);
        this.id = id;
        this.memberType = memberType;
    }
}
