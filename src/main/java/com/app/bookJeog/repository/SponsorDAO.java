package com.app.bookJeog.repository;


import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.mapper.SponsorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SponsorDAO {

    private final SponsorMapper sponsorMapper;

    // 단체 로그인
    public Optional<SponsorMemberVO> findSponsorMember (SponsorMemberVO sponsorMemberVO) {
       return sponsorMapper.loginSponsorMember(sponsorMemberVO);
    };


    // 이메일 중복검사
    public Optional<SponsorMemberVO> findSponsorMemberEmail(SponsorMemberVO sponsorMemberVO) {
       return sponsorMapper.selectSponsorMember(sponsorMemberVO);
    }


    // 비밀번호 변경
    public void updateSponsorMember(SponsorMemberVO sponsorMemberVO, String newPasswd) {
        sponsorMapper.updatePassword(sponsorMemberVO, newPasswd);
    }
}
