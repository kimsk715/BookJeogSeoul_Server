package com.app.bookJeog.repository;

import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {


    private final MemberMapper memberMapper;

    // 일반회원 회원가입
    public void setPersonalMember(PersonalMemberVO personalMemberVO) {
        memberMapper.insertPersonalMember(personalMemberVO);
    }


    // 슈퍼키 회원가입
    public void setCommonMember(MemberVO memberVO) {
        memberMapper.insertCommonMember(memberVO);
    }



    // 이메일 중복검사
    public Optional<PersonalMemberVO> findByEmail(String memberEmail) {
        return memberMapper.selectByEmail(memberEmail);
    }


    // 일반 로그인
    public

}
