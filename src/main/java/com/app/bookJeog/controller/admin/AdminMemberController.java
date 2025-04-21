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
import org.springframework.web.bind.annotation.ResponseBody;

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
    public AdminPersonalMemberDTO getAllPersonal(Pagination pagination) {
        AdminPersonalMemberDTO adminPersonalMemberDTO = new AdminPersonalMemberDTO();
        List<PersonalMemberVO> members = memberService.getAllPersonal(pagination);
        log.info(members.toString());
        List<PersonalMemberDTO> memberDTOS = new ArrayList<>();
        for (PersonalMemberVO member : members) {
            PersonalMemberDTO memberDTO = new PersonalMemberDTO();
            memberDTO.setId(member.getId());
            memberDTO.setMemberEmail(member.getMemberEmail());
            memberDTO.setMemberName(member.getMemberName());
            memberDTO.setMemberPhone(member.getMemberPhone());
            memberDTO.setMemberMileage(member.getMemberMileage());
            memberDTO.setMemberStatus(member.getMemberStatus());
            memberDTOS.add(memberDTO);
        }
        log.info(memberDTOS.toString());
        pagination.create(memberService.countAllPersonal(pagination));
        adminPersonalMemberDTO.setPagination(pagination);
        adminPersonalMemberDTO.setPersonalMemberDTOList(memberDTOS);
        log.info(adminPersonalMemberDTO.toString());
        return adminPersonalMemberDTO;
    }

    
}
