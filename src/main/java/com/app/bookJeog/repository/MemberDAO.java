package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.AdminVO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
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
    public Optional<PersonalMemberVO> findByEmail(PersonalMemberVO personalMemberVO) {
        return memberMapper.selectByEmail(personalMemberVO);
    }


    // 일반 로그인
    public Optional<PersonalMemberVO> findPersonalMember(PersonalMemberVO personalMemberVO) {
        return memberMapper.loginPersonal(personalMemberVO);
    }


    // 일반 비밀번호 찾기
    public Optional<PersonalMemberVO> findPersonalMemberPassword(PersonalMemberVO personalMemberVO) {
        return memberMapper.searchPassword(personalMemberVO);
    }

    // 시퀀스로 회원 찾기
    public MemberVO findById(Long id) {
        return memberMapper.selectById(id);
    }

    public PersonalMemberVO findPersonalMemberById(Long id){
        return memberMapper.selectPersonalMemberById(id);
    }

    public SponsorMemberVO findSponsorMemberById(Long id){
        return memberMapper.selectSponsorMemberById(id);
    }

    public void updatePersonalMemberStatus(Long memberId) {
        memberMapper.updatePersonalMemberStatus(memberId);
    }

    public void updateSponsorMemberStatus(Long sponsorId) {
        memberMapper.updateSponsorMemberStatus(sponsorId);
    }

    public List<SponsorMemberVO> findAllSponsor(Pagination pagination) {
        return memberMapper.selectAllSponsor(pagination);
    }

    public int countAllSponsor(Pagination pagination) {
        return memberMapper.countAllSponsor(pagination);
    }

    public void insertSponsorMember(SponsorMemberVO sponsorMemberVO) {
        memberMapper.insertSponsorMember(sponsorMemberVO);
    }

    public List<AdminVO> findAllAdmin(Pagination pagination) {
        return memberMapper.selectAllAdmin(pagination);
    }

    public int countAllAdmin(Pagination pagination) {
        return memberMapper.countAllAdmin(pagination);
    }

    public void setAdmin(AdminVO adminVO) {
        log.info(adminVO.toString());
        memberMapper.insertAdmin(adminVO);
    }

    // 비밀번호 변경
    public void setPassword(String memberEmail, String newPasswd) {
        memberMapper.updatePassword(memberEmail, newPasswd);
    }


}

