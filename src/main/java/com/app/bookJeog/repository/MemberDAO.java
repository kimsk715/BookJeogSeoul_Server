package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberDAO {
    private final MemberMapper memberMapper;

    public List<PersonalMemberVO> findAllPersonal(Pagination pagination) {

        return memberMapper.selectAllPersonal(pagination);
    }

    public int countAllPersonal(Pagination pagination) {
        return memberMapper.countAllPersonal(pagination);
    }



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
    public Optional<PersonalMemberVO> findPersonalMember(PersonalMemberVO personalMemberVO) {
        return memberMapper.loginPersonal(personalMemberVO);
    }


    // 일반 비밀번호 찾기
    public Optional<PersonalMemberVO> findPersonalMemberPassword(PersonalMemberVO personalMemberVO) {
        return memberMapper.searchPassword(personalMemberVO);
    }

}
