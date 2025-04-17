package com.app.bookJeog.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorInquiryFileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long sponsorInquiryId;


    public SponsorInquiryFileVO(Long id, Long sponsorInquiryId) {
        this.id = id;
        this.sponsorInquiryId = sponsorInquiryId;
    }
}
