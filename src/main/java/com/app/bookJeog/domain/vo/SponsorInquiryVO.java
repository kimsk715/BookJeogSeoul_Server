package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberInquiryType;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import com.app.bookJeog.domain.enumeration.SponsorInquiryStatus;
import com.app.bookJeog.domain.enumeration.SponsorInquiryType;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorInquiryVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long sponsorId;
    private SponsorInquiryType sponsorInquiryType;
    private String sponsorInquiryText;
    private String sponsorInquiryTitle;
    private String sponsorInquiryAnswer;
    private SponsorInquiryStatus sponsorInquiryStatus;


    @Builder
    public SponsorInquiryVO(String createdDate, String updatedDate, Long id, Long sponsorId, String sponsorInquiryAnswer, SponsorInquiryStatus sponsorInquiryStatus, String sponsorInquiryText, String sponsorInquiryTitle, SponsorInquiryType sponsorInquiryType) {
        super(createdDate, updatedDate);
        this.id = id;
        this.sponsorId = sponsorId;
        this.sponsorInquiryAnswer = sponsorInquiryAnswer;
        this.sponsorInquiryStatus = sponsorInquiryStatus;
        this.sponsorInquiryText = sponsorInquiryText;
        this.sponsorInquiryTitle = sponsorInquiryTitle;
        this.sponsorInquiryType = sponsorInquiryType;
    }
}
