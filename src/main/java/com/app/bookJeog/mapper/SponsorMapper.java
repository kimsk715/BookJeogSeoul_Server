package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.vo.SponsorMemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SponsorMapper {

    // 단체 로그인
    public Optional<SponsorMemberVO> loginSponsorMember (SponsorMemberVO sponsorMemberVO);
}
