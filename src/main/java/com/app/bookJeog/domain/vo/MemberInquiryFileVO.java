package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberInquiryFileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberInquiryId;


    public MemberInquiryFileVO(Long id, Long memberInquiryId) {
        this.id = id;
        this.memberInquiryId = memberInquiryId;
    }
}
