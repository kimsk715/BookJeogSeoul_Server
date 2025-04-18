package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {


    private final MemberMapper memberMapper;

    // 일반회원 회원가입
    public void insertPersonalMember(PersonalMemberVO personalMemberVO) {
        memberMapper.insertPersonalMember(personalMemberVO);
    }


    // 슈퍼키 회원가입
    public void insertCommonMember(MemberVO memberVO) {
        memberMapper.insertCommonMember(memberVO);
    }



    // 이메일 중복검사
    public Optional<PersonalMemberVO> selectByEmail(String memberEmail) {
        return memberMapper.selectByEmail(memberEmail);
    }

}
