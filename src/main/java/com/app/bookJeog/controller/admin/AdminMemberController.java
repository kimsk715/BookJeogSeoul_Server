package com.app.bookJeog.controller.admin;


<<<<<<< HEAD
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.AdminMemberStatus;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
=======
import com.app.bookJeog.domain.dto.AdminDTO;
import com.app.bookJeog.domain.dto.AdminPersonalMemberDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.vo.AdminVO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.AdminServiceImpl;
>>>>>>> 13d07a803c93b8113c950140ca4da15fc122cc96
import com.app.bookJeog.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;
<<<<<<< HEAD
    private final SponsorMemberDTO sponsorMemberDTO;
=======
    private final AdminServiceImpl adminServiceImpl;
    private HttpSession session;


    // 어드민 로그인
    @GetMapping("admin/login")
    public String goToAdmin() {
        return "login/admin";
    }

    @PostMapping("admin/login-admin")
    public String loginAdmin (AdminDTO adminDTO) {
        Optional<AdminVO> foundAdmin = adminServiceImpl.loginAdmin(adminDTO);
        if(foundAdmin.isPresent()) {
            session.setAttribute("admin", foundAdmin);
            return "redirect:/admin-page";
        } else {
            return "redirect:/admin/login?result=fail";
        }
    }


>>>>>>> 13d07a803c93b8113c950140ca4da15fc122cc96

    @GetMapping("admin/personal-members")
    @ResponseBody
    public AdminPersonalMemberDTO getAllPersonal(Pagination pagination, @RequestParam(value = "keyword", required = false) String keyword) {
        String decodedKeyword = "";
        if(keyword != null) {
            decodedKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
        }


        log.info("keyword: " + decodedKeyword);
        pagination.setKeyword(decodedKeyword);
        AdminPersonalMemberDTO adminPersonalMemberDTO = new AdminPersonalMemberDTO();
        pagination.create(memberService.countAllPersonal(pagination)); // 사이드
        adminPersonalMemberDTO.setPagination(pagination); // 사이드
        List<PersonalMemberVO> members = memberService.getAllPersonal(pagination); // 메인
        List<PersonalMemberDTO> memberDTOS = new ArrayList<>();
        // VO -> DTO
        for (PersonalMemberVO member : members) {
            PersonalMemberDTO memberDTO = new PersonalMemberDTO();
            memberDTO.setId(member.getId());
            memberDTO.setMemberEmail(member.getMemberEmail());
            memberDTO.setMemberName(member.getMemberName());
            memberDTO.setMemberPhone(member.getMemberPhone());
            memberDTO.setMemberMileage(member.getMemberMileage());
            memberDTO.setMemberStatus(member.getMemberStatus());
            memberDTO.setCreatedDate(member.getCreatedDate());
            memberDTO.setUpdatedDate(member.getUpdatedDate());
            memberDTOS.add(memberDTO);
        }
        adminPersonalMemberDTO.setPersonalMemberDTOList(memberDTOS);

        return adminPersonalMemberDTO;
    }
<<<<<<< HEAD

    // 단체 회원 목록 조회
    @GetMapping("admin/sponsor-members")
    @ResponseBody
    public AdminSponsorMemberDTO getAllSponsorMembers(Pagination pagination, @RequestParam(value = "keyword", required = false) String keyword) {
        AdminSponsorMemberDTO adminSponsorMemberDTO = new AdminSponsorMemberDTO();
        pagination.create(memberService.countAllSponsor(pagination));
        adminSponsorMemberDTO.setPagination(pagination);
        adminSponsorMemberDTO.setSponsorMemberDTOList(memberService.getAllSponsor(pagination));
        return adminSponsorMemberDTO;
    }

    // 단체 회원 등록하기
    @GetMapping("admin/insert-sponsor")
    @ResponseBody
    public void insertSponsorMember(@RequestParam("info") List<String> infoArray){
        SponsorMemberDTO sponsorMemberDTO = new SponsorMemberDTO();
        sponsorMemberDTO.setSponsorId(infoArray.get(0));
        sponsorMemberDTO.setSponsorPassword(infoArray.get(1));
        sponsorMemberDTO.setSponsorEmail(infoArray.get(2));
        sponsorMemberDTO.setSponsorPhoneNumber(infoArray.get(3));
        sponsorMemberDTO.setSponsorName(infoArray.get(4));
        sponsorMemberDTO.setSponsorMainAddress(infoArray.get(5));
        sponsorMemberDTO.setSponsorSubAddress(infoArray.get(6));
        memberService.insertSponsorMember(sponsorMemberDTO);
    }

    @GetMapping("admin/admin-members")
    @ResponseBody
    public AdminInfoDTO getAllAdmin(Pagination pagination, @RequestParam(value = "keyword", required = false) String keyword) {
        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();
        pagination.create(memberService.countAllAdmin(pagination));
        adminInfoDTO.setPagination(pagination);
        adminInfoDTO.setAdminDTOList(memberService.getAllAdmin(pagination));
        log.info(adminInfoDTO.toString());
        return adminInfoDTO;
    }

    @GetMapping("admin/insert-admin")
    @ResponseBody
    public void insertAdmin(@RequestParam("info") List<String> infoArray){
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminId(infoArray.get(0));
        adminDTO.setAdminPassword(infoArray.get(1));
        adminDTO.setAdminName(infoArray.get(2));
        adminDTO.setAdminDepartment(infoArray.get(3));
        adminDTO.setAdminGrade(infoArray.get(4));
        adminDTO.setAdminMemberStatus(AdminMemberStatus.ACTIVE);
        log.info(adminDTO.toString());
        memberService.insertAdmin(adminDTO);
    }
=======
>>>>>>> 13d07a803c93b8113c950140ca4da15fc122cc96
}
