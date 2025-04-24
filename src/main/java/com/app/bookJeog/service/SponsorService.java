package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.SponsorMemberDTO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;

import java.util.Optional;

public interface SponsorService {

    // 단체 로그인
    public Optional<SponsorMemberVO> loginSponsorMember (SponsorMemberDTO sponsorMemberDTO);

    public default SponsorMemberVO toSponsorMemberVO (SponsorMemberDTO sponsorMemberDTO) {
        return  SponsorMemberVO.builder()
                .id(sponsorMemberDTO.getId())
                .sponsorId(sponsorMemberDTO.getSponsorId())
                .sponsorPassword(sponsorMemberDTO.getSponsorPassword())
                .sponsorEmail(sponsorMemberDTO.getSponsorEmail())
                .sponsorName(sponsorMemberDTO.getSponsorName())
                .sponsorPhone(sponsorMemberDTO.getSponsorPhone())
                .sponsorMainAddress(sponsorMemberDTO.getSponsorMainAddress())
                .sponsorMemberStatus(sponsorMemberDTO.getSponsorMemberStatus())
                .sponsorSubAddress(sponsorMemberDTO.getSponsorSubAddress())
                .build();
    }


    // 비밀번호 변경
    public void changePassword(SponsorMemberDTO sponsorMemberDTO, String newPasswd);



    // 이메일 중복검사
    public Optional<SponsorMemberVO> selectEmailForPassword(SponsorMemberDTO sponsorMemberDTO);
}
