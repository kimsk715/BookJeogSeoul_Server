package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.dto.SponsormemberDTO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;

import java.util.Optional;

public interface SponsorService {

    // 단체 로그인
    public Optional<SponsorMemberVO> loginSponsorMember (SponsormemberDTO sponsorMemberDTO);

    public default SponsorMemberVO toSponsorMemberVO (SponsormemberDTO sponsorMemberDTO) {
        return  SponsorMemberVO.builder()
                .sponsorId(sponsorMemberDTO.getSponsorId())
                .sponsorPassword(sponsorMemberDTO.getSponsorPassword())
                .sponsorEmail(sponsorMemberDTO.getSponsorEmail())
                .build();
    }


    // 비밀번호 변경
    public void changePassword(SponsormemberDTO sponsorMemberDTO, String newPasswd);



    // 이메일 중복검사
    public Optional<SponsorMemberVO> selectEmailForPassword(SponsormemberDTO sponsorMemberDTO);
}
