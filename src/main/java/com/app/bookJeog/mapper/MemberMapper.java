package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.dto.PersonalMemberPostMemberProfileDTO;
import com.app.bookJeog.domain.dto.SponsorMemberProfileDTO;
import com.app.bookJeog.domain.vo.*;
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
    public Optional<PersonalMemberVO> selectByEmail(String email);

    // 기업회원 통합검색 조회
    public List<SponsorMemberProfileDTO> selectSponsorMembersWithProfile(String keyword);

    // 기업회원 검색 결과 개수 조회
    public int selectSponsorMembersTotal(String keyword);

    // 기업회원 전체페이지 조회(무한스크롤)
    public List<SponsorMemberProfileDTO> selectAllSponsorMembers(String keyword, int offset, String sortType);

    // 일반 로그인
    public Optional<PersonalMemberVO> loginPersonal(PersonalMemberVO personalMemberVO);


    // 일반 비밀번호 찾기
    public Optional<PersonalMemberVO> searchPassword(PersonalMemberVO personalMemberVO);

    // 시퀀스(id)로 회원 찾기

    MemberVO selectById(Long id);

    PersonalMemberVO selectPersonalMemberById(Long id);

    SponsorMemberVO selectSponsorMemberById(Long id);

    // 개인 회원 활동 상태 변경(신고)
    public void updatePersonalMemberStatus(Long memberId);

    // 단체 회원 활동 상태 변경(신고)
    public void updateSponsorMemberStatus(Long sponsorId);


    public List<SponsorMemberVO> selectAllSponsor(Pagination pagination);

    public int countAllSponsor(Pagination pagination);

    public void insertSponsorMember(SponsorMemberVO sponsorMemberVO);

    public List<AdminVO> selectAllAdmin(Pagination pagination);

    public int countAllAdmin(Pagination pagination);

    public void insertAdmin(AdminVO adminVO);

    // 비밀번호 변경
    public void updatePassword(String memberEmail, String newPasswd);


    // 독후감 많이쓴 멤버 조회
    public List<PersonalMemberVO> selectTopBookPostMember ();


    // 독후감 많이쓴 멤버 조회 썸넬 포함
    public List<PersonalMemberPostMemberProfileDTO> selectMemberInfoWithThumbnail ();

    // 내가 쓴 전체 독후감 개수 조회
    public int selectMyBookPostCount(Long memberId);

    // 내 이번 달 쓴 독후감 수
    public int selectMyMonthlyBookPostCount(Long memberId);

    // 이번 달 독후감 쓴 회원들의 평균 독후감
    public int selectAverageBookPostCount();

    // 내 마일리지 조회
    public int selectMyMileage(Long memberId);

    // 내 프로필 이미지 조회
    public FileVO selectMyProfile(Long memberId);
}
