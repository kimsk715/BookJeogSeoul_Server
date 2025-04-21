package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminMemberInquiryDTO;
import com.app.bookJeog.domain.dto.MemberInquiryDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.service.InquiryService;
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
public class AdminInquiryController {
    private final InquiryService inquiryService;

    @GetMapping("admin/member-inquiries")
    @ResponseBody
    public AdminMemberInquiryDTO memberInquiry(Pagination pagination) {
        AdminMemberInquiryDTO adminMemberInquiryDTO = new AdminMemberInquiryDTO();
        pagination.create(inquiryService.countAllMemberInquiry());
        adminMemberInquiryDTO.setPagination(pagination);
        log.info(pagination.toString());
        List<MemberInquiryVO> tempList = inquiryService.getAllMemberInquiry(pagination);
        log.info(tempList.toString());
        List<MemberInquiryDTO> memberInquiryDTOList = new ArrayList<>();
        for (MemberInquiryVO memberInquiry : tempList) {
            MemberInquiryDTO memberInquiryDTO = inquiryService.toMemberInquiryDTO(memberInquiry);
            memberInquiryDTOList.add(memberInquiryDTO);
            log.info(memberInquiryDTO.toString());
        }
        adminMemberInquiryDTO.setMemberInquiryDTOList(memberInquiryDTOList);
        log.info(adminMemberInquiryDTO.toString());
        return adminMemberInquiryDTO;
    }
}
