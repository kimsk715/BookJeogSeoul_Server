package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.MemberInquiryFileVO;
import com.app.bookJeog.domain.vo.MemberProfileVO;
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
public class MemberProfileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;

    public MemberProfileVO toVO() {
        return MemberProfileVO.builder()
                .id(id)
                .memberId(memberId)
                .build();
    }


}
