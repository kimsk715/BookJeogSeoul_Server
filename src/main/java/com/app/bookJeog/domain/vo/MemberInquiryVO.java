package com.app.bookJeog.domain.vo;

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

}
