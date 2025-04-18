package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.MemberVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private MemberType memberType;
    private String createdDate;
    private String updatedDate;

    public MemberVO toVO(){
        return MemberVO.builder()
                .id(id)
                .memberType(memberType)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
