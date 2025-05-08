package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.SponsorPostDTO;
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
    public void updateSponsorPasswd(SponsorMemberVO sponsorMemberVO);

    // 마이페이지 기업회원 조회
    public SponsorPostDTO selectSponsorMypage(Long sponsorId);
}
