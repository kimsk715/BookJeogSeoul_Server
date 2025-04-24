package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.SponsormemberDTO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SponsorMapper {

    // 단체 로그인
    public Optional<SponsorMemberVO> loginSponsorMember (SponsorMemberVO sponsorMemberVO);


    // 이메일 중복검사
    public Optional<SponsorMemberVO> selectSponsorMember (SponsorMemberVO sponsorMemberVO);


    // 비밀번호 변경
    public void updatePassword(SponsorMemberVO sponsorMemberVO, String newPasswd);
}
