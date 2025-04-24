package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.SponsorMemberStatus;
import com.app.bookJeog.domain.vo.MemberInquiryFileVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
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
public class SponsormemberDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String sponsorId;
    private String sponsorPassword;
    private String sponsorName;
    private String sponsorPhone;
    private String sponsorMainAddress;
    private String sponsorEmail;
    private String sponsorSubAddress;
    private SponsorMemberStatus sponsorMemberStatus;

    public SponsorMemberVO toVO() {
        return SponsorMemberVO.builder()
                .id(id)
                .sponsorId(sponsorId)
                .sponsorPassword(sponsorPassword)
                .sponsorName(sponsorName)
                .sponsorEmail(sponsorEmail)
                .sponsorPhone(sponsorPhone)
                .sponsorMainAddress(sponsorMainAddress)
                .sponsorSubAddress(sponsorSubAddress)
                .sponsorMemberStatus(sponsorMemberStatus)
                .build();
    }


}
