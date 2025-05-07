package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.PersonalMemberPostMemberProfileDTO;
import com.app.bookJeog.domain.dto.SponsorMemberProfileDTO;
import com.app.bookJeog.domain.vo.*;
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
    public Optional<PersonalMemberVO> findByEmail(String email) {
        return memberMapper.selectByEmail(email);
    }


    // 기업회원 통합검색 조회
    public List<SponsorMemberProfileDTO> findSponsorMembersWithProfile(String keyword){
        return memberMapper.selectSponsorMembersWithProfile(keyword);
    };

    // 기업회원 검색 결과 개수 조회
    public int findSponsorMembersTotal(String keyword){
        return memberMapper.selectSponsorMembersTotal(keyword);
    };

    // 기업회원 전체페이지 조회(무한스크롤)
    public List<SponsorMemberProfileDTO> findAllSponsorMembers(String keyword, int offset, String sortType) {
        return memberMapper.selectAllSponsorMembers(keyword, offset, sortType);
    };

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



    // 독후감 많이 쓴 사람
    public List<PersonalMemberVO> findTopBookPostMember(){
        return memberMapper.selectTopBookPostMember();
    }



    // 독후감 많이 쓴 사람
    public List<PersonalMemberPostMemberProfileDTO> findMemberInfoWithThumbnail(){
        return memberMapper.selectMemberInfoWithThumbnail();
    }

    // 내가 쓴 전체 독후감 개수 조회
    public int findMyBookPostCount(Long memberId){return memberMapper.selectMyBookPostCount(memberId);};

    // 내 이번 달 쓴 독후감 수
    public int findMyMonthlyBookPostCount(Long memberId){return memberMapper.selectMyMonthlyBookPostCount(memberId);};

    // 이번 달 독후감 쓴 회원들의 평균 독후감
    public int findAverageBookPostCount(){return memberMapper.selectAverageBookPostCount();};

    // 내 마일리지 조회
    public int findMyMileage(Long memberId) {return memberMapper.selectMyMileage(memberId);};

    // 내 프로필 이미지 조회
    public FileVO findMyProfile(Long memberId){return memberMapper.selectMyProfile(memberId);};

    // 비밀번호 유효성검사
    public boolean checkPassword(Long memberId, String password){return memberMapper.checkPassword(memberId, password);};

    // 회원 프사 조회, 삭제, 변경
    public Long selectProfileFileId(Long memberId){return memberMapper.selectProfileFileId(memberId);};

    public void updateMemberFile(FileVO fileVO){memberMapper.updateMemberFile(fileVO);};

    public void deleteMemberFile(Long id){memberMapper.deleteMemberFile(id);};
    public void deleteMemberProfile(Long memberId){memberMapper.deleteMemberProfile(memberId);};

    // 프사 만들기
    public void insertProfileFile(FileVO fileVO){memberMapper.insertProfileFile(fileVO);};
    public void insertMemberProfile(MemberProfileVO memberProfileVO){memberMapper.insertMemberProfile(memberProfileVO);};

    // 개인회원 닉네임 변경
    public void updateNickname(PersonalMemberVO personalMemberVO){memberMapper.updateNickname(personalMemberVO);};

    // 개인회원 비밀번호 변경
    public void updateMemberPassword(PersonalMemberVO personalMemberVO){memberMapper.updateMemberPassword(personalMemberVO);};
}

