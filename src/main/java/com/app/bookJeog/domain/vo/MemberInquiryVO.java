package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberInquiryStatus;
import com.app.bookJeog.domain.enumeration.MemberInquiryType;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor

public class MemberInquiryVO extends Period {
    private Long id;
    private Long memberId;
    private MemberInquiryType memberInquiryType;
    private String memberInquiryText;
    private String memberInquiryTitle;
    private String memberInquiryAnswer;
    private MemberInquiryStatus memberInquiryStatus;

}
