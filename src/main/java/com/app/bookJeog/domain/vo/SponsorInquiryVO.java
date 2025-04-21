package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberInquiryType;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import com.app.bookJeog.domain.enumeration.SponsorInquiryStatus;
import com.app.bookJeog.domain.enumeration.SponsorInquiryType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
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

}
