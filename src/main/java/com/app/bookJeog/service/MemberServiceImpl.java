package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import com.app.bookJeog.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final MemberMapper memberMapper;


    // 이메일 중복검사
    public Optional<PersonalMemberVO> checkEmail(String email) {
        return memberMapper.selectByEmail(email);
    }


    //일반회원 회원가입
    public void insertPersonalMember(PersonalMemberDTO personalMemberDTO) {

         memberDAO.insertCommonMember();


    }
}
