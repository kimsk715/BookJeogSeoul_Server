package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.SponsorInquiryStatus;
import com.app.bookJeog.domain.enumeration.SponsorInquiryType;
import com.app.bookJeog.domain.vo.SponsorInquiryVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorInquiryDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long sponsorId;
    private SponsorInquiryType sponsorInquiryType;
    private String sponsorInquiryText;
    private String sponsorInquiryTitle;
    private String sponsorInquiryAnswer;
    private SponsorInquiryStatus sponsorInquiryStatus;
    private String createdDate;
    private String updatedDate;

    @Builder
    public SponsorInquiryVO toVO() {
        return SponsorInquiryVO.builder()
                .id(id)
                .sponsorId(sponsorId)
                .sponsorInquiryType(sponsorInquiryType)
                .sponsorInquiryText(sponsorInquiryText)
                .sponsorInquiryTitle(sponsorInquiryTitle)
                .sponsorInquiryAnswer(sponsorInquiryAnswer)
                .sponsorInquiryStatus(sponsorInquiryStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }


}
