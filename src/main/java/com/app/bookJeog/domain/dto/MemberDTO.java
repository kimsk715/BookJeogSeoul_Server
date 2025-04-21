package com.app.bookJeog.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.MemberVO;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private MemberType memberType;

    public MemberVO toVO() {
        return MemberVO.builder()
                .id(id)
                .memberType(memberType)
                .build();

    }

}
