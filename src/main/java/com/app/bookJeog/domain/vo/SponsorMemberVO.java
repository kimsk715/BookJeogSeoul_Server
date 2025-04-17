package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import com.app.bookJeog.domain.enumeration.SponsorMemberStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorMemberVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String sponsorId;
    private String sponsorPassword;
    private String sponsorName;
    private String sponsorPhone;
    private String sponsorMainAddress;
    private String sponsorSubAddress;
    private SponsorMemberStatus sponsorMemberStatus;

    @Builder
    public SponsorMemberVO(Long id, String sponsorId, String sponsorMainAddress, SponsorMemberStatus sponsorMemberStatus, String sponsorName, String sponsorPassword, String sponsorPhone, String sponsorSubAddress) {
        this.id = id;
        this.sponsorId = sponsorId;
        this.sponsorMainAddress = sponsorMainAddress;
        this.sponsorMemberStatus = sponsorMemberStatus;
        this.sponsorName = sponsorName;
        this.sponsorPassword = sponsorPassword;
        this.sponsorPhone = sponsorPhone;
        this.sponsorSubAddress = sponsorSubAddress;
    }
}
