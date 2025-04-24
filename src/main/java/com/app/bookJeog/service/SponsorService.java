package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.SponsormemberDTO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;

import java.util.Optional;

public interface SponsorService {

    // 단체 로그인
    public Optional<SponsorMemberVO> loginSponsorMember (SponsormemberDTO sponsorMemberDTO);

    public default SponsorMemberVO toSponsorMemberVO (SponsormemberDTO sponsorMemberDTO) {
        return  SponsorMemberVO.builder()
                .sponsorId(sponsorMemberDTO.getSponsorId())
                .sponsorPassword(sponsorMemberDTO.getSponsorPassword())
                .build();
    }
}
