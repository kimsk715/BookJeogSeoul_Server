package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberInquiryType;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberInquiryVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private MemberInquiryType memberInquiryType;
    private String memberInquiryText;
    private String memberInquiryTitle;
    private String memberInquiryAnswer;
    private PersonalMemberStatus memberInquiryStatus;



    @Builder
    public MemberInquiryVO(String createdDate, String updatedDate, Long id, Long memberId, String memberInquiryAnswer, PersonalMemberStatus memberInquiryStatus, String memberInquiryText, String memberInquiryTitle, MemberInquiryType memberInquiryType) {
        super(createdDate, updatedDate);
        this.id = id;
        this.memberId = memberId;
        this.memberInquiryAnswer = memberInquiryAnswer;
        this.memberInquiryStatus = memberInquiryStatus;
        this.memberInquiryText = memberInquiryText;
        this.memberInquiryTitle = memberInquiryTitle;
        this.memberInquiryType = memberInquiryType;
    }
}
