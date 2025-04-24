package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {
        public List<PersonalMemberVO> getAllPersonal(Pagination pagination);

        public int countAllPersonal(Pagination pagination);

    // 회원가입
    public default MemberVO toMemberVO  () {
        return MemberVO.builder().memberType(MemberType.PERSONAL).build();
    }

    public default MemberVO toSponsorMember() {
        return MemberVO.builder().memberType(MemberType.SPONSOR).build();
    }

    public default MemberVO toAdmin() {
        return MemberVO.builder().memberType(MemberType.ADMIN).build();
    }
    public default PersonalMemberVO toPersonalMemberVO(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        return PersonalMemberVO.builder()
                .id(memberPersonalMemberDTO.getId())
                .memberBirth(memberPersonalMemberDTO.getMemberBirth())
                .memberPassword(memberPersonalMemberDTO.getMemberPassword())
                .memberName(memberPersonalMemberDTO.getMemberName())
                .memberGender(memberPersonalMemberDTO.getMemberGender())
                .memberPhone(memberPersonalMemberDTO.getMemberPhone())
                .memberEmail(memberPersonalMemberDTO.getMemberEmail())
                .memberNickName(memberPersonalMemberDTO.getMemberNickName())
                .build();
    }


    // 이메일 중복검사
    public Optional<PersonalMemberVO> checkEmail(String email);


    // 로그인 ( 화면에서 입력받은 DTO 를 VO로 )
    public default PersonalMemberVO toPersonalMemberVO(PersonalMemberDTO personalMemberDTO) {
        return PersonalMemberVO.builder()
                .memberEmail(personalMemberDTO.getMemberEmail())
                .memberPassword(personalMemberDTO.getMemberPassword())
                .memberName(personalMemberDTO.getMemberName())
                .memberGender(personalMemberDTO.getMemberGender())
                .memberBirth(personalMemberDTO.getMemberBirth())
                .build();
    }
    

    public default PersonalMemberDTO toPersonalMemberDTO(PersonalMemberVO personalMemberVO) {
        PersonalMemberDTO personalMemberDTO = new PersonalMemberDTO();
        personalMemberDTO.setMemberEmail(personalMemberVO.getMemberEmail());
        personalMemberDTO.setMemberPassword(personalMemberVO.getMemberPassword());
        personalMemberDTO.setMemberName(personalMemberVO.getMemberName());
        personalMemberDTO.setMemberGender(personalMemberVO.getMemberGender());
        personalMemberDTO.setMemberPhone(personalMemberVO.getMemberBirth());
        return personalMemberDTO;
    }

    public void updateMemberStatus(Long memberId);

    public MemberVO getById(Long id);

    public PersonalMemberVO getPersonalMember(Long memberId);

    public List<SponsorMemberDTO> getAllSponsor(Pagination pagination);

    public default SponsorMemberDTO toSponsorMemberDTO(SponsorMemberVO sponsorMemberVO) {
        SponsorMemberDTO sponsorMemberDTO = new SponsorMemberDTO();
        sponsorMemberDTO.setId(sponsorMemberVO.getId());
        sponsorMemberDTO.setSponsorId(sponsorMemberVO.getSponsorId());
        sponsorMemberDTO.setSponsorEmail(sponsorMemberVO.getSponsorEmail());
        sponsorMemberDTO.setSponsorName(sponsorMemberVO.getSponsorName());
        sponsorMemberDTO.setSponsorPassword(sponsorMemberVO.getSponsorPassword());
        sponsorMemberDTO.setSponsorMemberStatus(sponsorMemberVO.getSponsorMemberStatus());
        sponsorMemberDTO.setSponsorPhoneNumber(sponsorMemberVO.getSponsorPhoneNumber());
        sponsorMemberDTO.setSponsorMainAddress(sponsorMemberVO.getSponsorMainAddress());
        sponsorMemberDTO.setSponsorSubAddress(sponsorMemberVO.getSponsorSubAddress());
        sponsorMemberDTO.setCreatedDate(sponsorMemberVO.getCreatedDate());
        sponsorMemberDTO.setUpdatedDate(sponsorMemberVO.getUpdatedDate());

        return sponsorMemberDTO;

    }

    public int countAllSponsor(Pagination pagination);

    public void insertSponsorMember(SponsorMemberDTO sponsorMemberDTO);

    public List<AdminDTO> getAllAdmin(Pagination pagination);

    public int countAllAdmin(Pagination pagination);

    public void insertAdmin(AdminDTO adminDTO);
}

