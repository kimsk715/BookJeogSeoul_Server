package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;


@Mapper
public interface MemberMapper {
    // 관리자 페이지 개인 회원 조회
    public List<PersonalMemberVO> selectAllPersonal(Pagination pagination);

    // 관리자 페이지 개인 회원 페이지네이션
    public int countAllPersonal(Pagination pagination);

    // 일반 회원 회원가입
    public void insertPersonalMember(PersonalMemberVO personalMemberVO);


    // 회원 슈퍼키 등록
    public void insertCommonMember(MemberVO memberVO);


    // 이메일 중복검사
    public Optional<PersonalMemberVO> selectByEmail(String memberEmail);


    // 일반 로그인
    public Optional<PersonalMemberVO> loginPersonal(PersonalMemberVO personalMemberVO);


    // 일반 비밀번호 찾기
    public Optional<PersonalMemberVO> searchPassword(PersonalMemberVO personalMemberVO);

    // 시퀀스(id)로 회원 찾기

    MemberVO selectById(Long id);

    PersonalMemberVO selectPersonalMemberById(Long id);

    SponsorMemberVO selectSponsorMemberById(Long id);
}
