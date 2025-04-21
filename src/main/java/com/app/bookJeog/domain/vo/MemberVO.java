package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberType;
import lombok.*;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.app.bookJeog.domain.vo.Period;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private MemberType memberType;


    // Period에서 받은 createdDate, updatedDate를 그대로 사용
    public MemberVO(String createdDate, String updatedDate, Long id, MemberType memberType) {
        super(createdDate, updatedDate);  // 부모 클래스에서 처리된 필드를 그대로 받음
        this.id = id;
        this.memberType = memberType;
    }
}
