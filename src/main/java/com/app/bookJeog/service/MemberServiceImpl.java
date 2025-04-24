package com.app.bookJeog.service;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.AdminVO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import com.app.bookJeog.repository.MemberDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final MemberMapper memberMapper;
    private final PersonalMemberDTO personalMemberDTO;
    private final HttpSession session;
    private final PersonalMemberVO personalMemberVO;


    @Override
    public List<PersonalMemberVO> getAllPersonal(Pagination pagination) {
        return memberDAO.findAllPersonal(pagination);
    }

    @Override
    public int countAllPersonal(Pagination pagination) {
        return memberDAO.countAllPersonal(pagination);
    }


    // 이메일 중복검사
    @Override
    public Optional<PersonalMemberVO> checkEmail(String email) {
        return memberDAO.findByEmail(email);
    }

    // 회원 타입에 따라 상태 변경(개인 회원이면 개인 회원 테이블에 영향
    @Override
    public void updateMemberStatus(Long memberId) {
        if(memberDAO.findById(memberId).getMemberType().getCode().equals("개인")){
            memberDAO.updatePersonalMemberStatus(memberId);
        }
        else{
            memberDAO.updateSponsorMemberStatus(memberId);
        }
    }

    @Override
    public MemberVO getById(Long id) {
        return memberDAO.findById(id);
    }

    @Override
    public PersonalMemberVO getPersonalMember(Long memberId) {
        return memberDAO.findPersonalMemberById(memberId);
    }

    @Override
    public List<SponsorMemberDTO> getAllSponsor(Pagination pagination) {
        List<SponsorMemberDTO> sponsorMemberDTOList = new ArrayList<>();
        List<SponsorMemberVO> tempList = memberDAO.findAllSponsor(pagination);
        for(SponsorMemberVO sponsorMemberVO : tempList){
            SponsorMemberDTO sponsorMemberDTO = new SponsorMemberDTO();
            sponsorMemberDTO.setId(sponsorMemberVO.getId());
            sponsorMemberDTO.setSponsorId(sponsorMemberVO.getSponsorId());
            sponsorMemberDTO.setSponsorPassword(sponsorMemberVO.getSponsorPassword());
            sponsorMemberDTO.setSponsorName(sponsorMemberVO.getSponsorName());
            sponsorMemberDTO.setSponsorEmail(sponsorMemberVO.getSponsorEmail());
            sponsorMemberDTO.setSponsorPhoneNumber(sponsorMemberVO.getSponsorPhoneNumber());
            sponsorMemberDTO.setSponsorMainAddress(sponsorMemberVO.getSponsorMainAddress());
            sponsorMemberDTO.setSponsorSubAddress(sponsorMemberVO.getSponsorSubAddress());
            sponsorMemberDTO.setSponsorMemberStatus(sponsorMemberVO.getSponsorMemberStatus());
            sponsorMemberDTO.setCreatedDate(sponsorMemberVO.getCreatedDate());
            sponsorMemberDTO.setUpdatedDate(sponsorMemberVO.getUpdatedDate());
            sponsorMemberDTOList.add(sponsorMemberDTO);
        }

        return sponsorMemberDTOList;
    }

    @Override
    public int countAllSponsor(Pagination pagination) {
        return memberDAO.countAllSponsor(pagination);
    }

    @Override
    public void insertSponsorMember(SponsorMemberDTO sponsorMemberDTO) {
        MemberVO memberVO = toSponsorMember();
        memberDAO.setCommonMember(memberVO);
        sponsorMemberDTO.setId(memberVO.getId());
        memberDAO.insertSponsorMember(sponsorMemberDTO.toVO());
    }

    @Override
    public List<AdminDTO> getAllAdmin(Pagination pagination) {
        List<AdminDTO> adminDTOList = new ArrayList<>();
        List<AdminVO> tempList = memberDAO.findAllAdmin(pagination);
        for(AdminVO adminVO : tempList){
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setId(adminVO.getId());
            adminDTO.setAdminId(adminVO.getAdminId());
            adminDTO.setAdminPassword(adminVO.getAdminPassword());
            adminDTO.setAdminDepartment(adminVO.getAdminDepartment());
            adminDTO.setAdminGrade(adminVO.getAdminGrade());
            adminDTO.setAdminName(adminVO.getAdminName());
            adminDTO.setAdminMemberStatus(adminVO.getAdminMemberStatus());
            adminDTO.setCreatedDate(adminVO.getCreatedDate());
            adminDTO.setUpdatedDate(adminVO.getUpdatedDate());
            adminDTOList.add(adminDTO);
        }

        return adminDTOList;
    }

    @Override
    public int countAllAdmin(Pagination pagination) {
        return memberDAO.countAllAdmin(pagination);
    }

    @Override
    public void insertAdmin(AdminDTO adminDTO) {
        MemberVO memberVO = toAdmin();
        memberDAO.setCommonMember(memberVO);
        log.info(memberVO.toString());
        adminDTO.setId(memberVO.getId());
        log.info(adminDTO.toString());
        memberDAO.setAdmin(adminDTO.toVO());
    }


    // 닉네임 중복검사 만들어야함
    //일반회원 회원가입
    public void insertPersonalMember(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        MemberVO memberVO = toMemberVO();
        memberDAO.setCommonMember(memberVO);
        memberPersonalMemberDTO.setId(memberVO.getId());
        PersonalMemberVO personalMemberVO = toPersonalMemberVO(memberPersonalMemberDTO);
        personalMemberVO = toPersonalMemberVO(memberPersonalMemberDTO);
        memberDAO.setPersonalMember(personalMemberVO);
    }


    // 로그인
    public Optional<PersonalMemberDTO> loginPersonalMember(PersonalMemberDTO personalMemberDTO) {

        PersonalMemberVO personalMemberVO = toPersonalMemberVO(personalMemberDTO);

        Optional<PersonalMemberVO> foundMember = memberDAO.findPersonalMember(personalMemberVO);

        return  foundMember.map(this::toPersonalMemberDTO);
    }


    // 비밀번호 찾기
    public Optional<PersonalMemberDTO> searchPassword (PersonalMemberDTO personalMemberDTO) {

        PersonalMemberVO personalMemberVO = toPersonalMemberVO(personalMemberDTO);
        log.info("searchPassword: personalMemberVO={}", personalMemberVO);

        Optional<PersonalMemberVO> foundMember = memberDAO.findPersonalMemberPassword(personalMemberVO);

        return foundMember.map(this::toPersonalMemberDTO);
    }
}
