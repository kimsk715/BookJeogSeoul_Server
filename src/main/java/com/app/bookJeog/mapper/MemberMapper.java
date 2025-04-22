package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.SponsorMemberProfileDTO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
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

    // 기업회원 통합검색 조회
    public List<SponsorMemberProfileDTO> selectSponsorMembersWithProfile(String keyword);

    // 기업회원 검색 결과 개수 조회
    public int selectSponsorMembersTotal(String keyword);

    // 기업회원 전체페이지 조회(무한스크롤)
    public List<SponsorMemberProfileDTO> selectAllSponsorMembers(String keyword, int offset, String sortType);
}
