package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminPersonalMemberDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

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

    
}
