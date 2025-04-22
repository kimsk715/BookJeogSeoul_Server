package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.MemberInquiryStatus;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.domain.vo.SponsorInquiryVO;
import com.app.bookJeog.service.InquiryService;
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
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminInquiryController {
    private final InquiryService inquiryService;

    @GetMapping("admin/member-inquiries")
    @ResponseBody
    public AdminMemberInquiryDTO memberInquiry(Pagination pagination, @RequestParam("page") int page, @RequestParam(value = "keyword", required = false) String keyword) {
        String decodedKeyword = "";
        if (keyword != null) {
            decodedKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
        }
        AdminMemberInquiryDTO adminMemberInquiryDTO = new AdminMemberInquiryDTO();
        pagination.create(inquiryService.countAllMemberInquiry(pagination));
        pagination.setKeyword(decodedKeyword);
        adminMemberInquiryDTO.setPagination(pagination);
        List<MemberInquiryVO> tempList = inquiryService.getAllMemberInquiry(pagination);
        List<MemberInquiryDTO> memberInquiryDTOList = new ArrayList<>();
        for (MemberInquiryVO memberInquiry : tempList) {
            MemberInquiryDTO memberInquiryDTO = inquiryService.toMemberInquiryDTO(memberInquiry);
            memberInquiryDTOList.add(memberInquiryDTO);
        }
        adminMemberInquiryDTO.setMemberInquiryDTOList(memberInquiryDTOList);
        return adminMemberInquiryDTO;
    }

    @GetMapping("admin/member-inquiry")
    @ResponseBody
    public MemberInquiryDTO memberInquiryDetail(@RequestParam("id") Long inquiryId) {
        MemberInquiryDTO memberInquiryDTO = new MemberInquiryDTO();
        Optional<MemberInquiryVO> foundInquiry = inquiryService.getMemberInquiry(inquiryId);
        memberInquiryDTO = inquiryService.toMemberInquiryDTO(foundInquiry.get());
        return memberInquiryDTO;
    }

    @GetMapping("admin/answer-personal")
    @ResponseBody
    public void answerPersonal(@RequestParam("id") Long inquiryId, @RequestParam("answer") String inquiryAnswer) {;
        MemberInquiryDTO memberInquiryDTO = new MemberInquiryDTO();
        memberInquiryDTO.setMemberInquiryAnswer(inquiryAnswer);
        memberInquiryDTO.setId(inquiryId);
//        log.info("컨트롤러 : {}",memberInquiryDTO.toMemberInquiryVO().toString());
        if(inquiryAnswer != null) {
           memberInquiryDTO.setMemberInquiryStatus(MemberInquiryStatus.DONE);
        }
        inquiryService.answerPersonalInquiry(memberInquiryDTO.toMemberInquiryVO());

    }

    @GetMapping("admin/sponsor-inquiries")
    @ResponseBody
    public AdminSponsorInquiryDTO sponsorInquiry(Pagination pagination, @RequestParam("page") int page, @RequestParam(value = "keyword", required = false) String keyword) {
        String decodedKeyword = "";
        if (keyword != null) {
            decodedKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
        }

        AdminSponsorInquiryDTO adminSponsorInquiryDTO = new AdminSponsorInquiryDTO();
        pagination.create(inquiryService.countAllSponsorInquiry(pagination));
        pagination.setKeyword(decodedKeyword);
        adminSponsorInquiryDTO.setPagination(pagination);
        List<SponsorInquiryVO> tempList = inquiryService.getAllSponsorInquiry(pagination);
        List<SponsorInquiryDTO> sponsorInquiryDTOList = new ArrayList<>();
        for (SponsorInquiryVO sponsorInquiry : tempList) {
            SponsorInquiryDTO sponsorInquiryDTO = inquiryService.tosponsorInquiryDTO(sponsorInquiry);
            sponsorInquiryDTOList.add(sponsorInquiryDTO);
//            log.info(SponsorInquiryDTO.toString());
        }
        adminSponsorInquiryDTO.setSponsorInquiryDTOList(sponsorInquiryDTOList);
//        log.info(adminMemberInquiryDTO.toString());
        return adminSponsorInquiryDTO;
    }
}
