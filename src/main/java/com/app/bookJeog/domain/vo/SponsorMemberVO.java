package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import com.app.bookJeog.domain.enumeration.SponsorMemberStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorMemberVO extends MemberVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String sponsorId;
    private String sponsorPassword;
    private String sponsorName;
    private String sponsorPhoneNumber;
    private String sponsorEmail;
    private String sponsorMainAddress;
    private String sponsorSubAddress;
    private SponsorMemberStatus sponsorMemberStatus;

}
