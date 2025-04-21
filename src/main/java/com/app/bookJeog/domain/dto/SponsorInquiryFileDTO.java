package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.PostAlarmVO;
import com.app.bookJeog.domain.vo.SponsorInquiryFileVO;
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
public class SponsorInquiryFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long sponsorInquiryId;

    public SponsorInquiryFileVO toVO() {
        return SponsorInquiryFileVO.builder()
                .id(id)
                .sponsorInquiryId(sponsorInquiryId)
                .build();

    }

}
