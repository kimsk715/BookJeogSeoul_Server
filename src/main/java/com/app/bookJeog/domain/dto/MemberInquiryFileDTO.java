package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.MemberInquiryFileVO;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
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
public class MemberInquiryFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberInquiryId;

    public MemberInquiryFileVO toVO() {
        return MemberInquiryFileVO.builder()
                .id(id)
                .memberInquiryId(memberInquiryId)
                .build();
    }


}
