package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.service.BookDonateService;
import com.app.bookJeog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminBookDonateController {

    private final BookDonateService bookDonateService;
    private final BookDonateDTO bookDonateDTO;
    private final MemberService memberService;


    @GetMapping("admin/book-donations")
    @ResponseBody
    public AdminBookDonateDTO getAllDonations(Pagination pagination) {
        AdminBookDonateDTO adminBookDonateDTO = new AdminBookDonateDTO();
        pagination.create(bookDonateService.countAllDonation(pagination));
        adminBookDonateDTO.setPagination(pagination);
        adminBookDonateDTO.setBookDonateMemberDTOList(bookDonateService.getAllDonation(pagination));
        return adminBookDonateDTO;
    }

    @GetMapping("admin/book-donation")
    @ResponseBody
    public BookDonateMemberDTO getDonation(@RequestParam("id") Long id) {
           BookDonateMemberDTO bookDonateMemberDTO = new BookDonateMemberDTO();
           BookDonateDTO bookDonateDTO = bookDonateService.toBookDonateDTO(bookDonateService.getDonation(id));
           bookDonateMemberDTO.setBookDonateDTO(bookDonateDTO);
           bookDonateMemberDTO.setMemberName(memberService.getPersonalMember(bookDonateDTO.getMemberId()).getMemberName());
        return bookDonateMemberDTO;
    }

    @GetMapping("admin/donate-ok")
    @ResponseBody
    public void updateStatus(@RequestParam List<Long> idList) {
        for(Long id : idList) {
            bookDonateService.updateStatus(id);
        }
    }

    @GetMapping("admin/donate-fail")
    @ResponseBody
    public void cancelStatus(@RequestParam List<Long> idList) {
        for(Long id : idList) {
            bookDonateService.cancelStatus(id);
        }
    }
}
