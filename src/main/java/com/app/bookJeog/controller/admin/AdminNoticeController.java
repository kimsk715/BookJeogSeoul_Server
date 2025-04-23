package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminNoticeDTO;
import com.app.bookJeog.domain.dto.NoticeDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.NoticeVO;
import com.app.bookJeog.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticeService noticeService;

    @GetMapping("admin/notices")
    @ResponseBody
    public AdminNoticeDTO getAdminNotice(Pagination pagination) {
        AdminNoticeDTO adminNoticeDTO = new AdminNoticeDTO();
        pagination.create(noticeService.countAllNotice(pagination));
        adminNoticeDTO.setPagination(pagination);
        adminNoticeDTO.setNoticeDTOList(noticeService.getAllNotice(pagination));
        return adminNoticeDTO;
    }

    @GetMapping("admin/notice")
    @ResponseBody
    public NoticeDTO getNotice(@RequestParam("id") Long id) {
        NoticeVO noticeVO = noticeService.selectNoticeById(id);
        return noticeService.toNoticeDTO(noticeVO);
    }

    @GetMapping("admin/notice-edit")
    @ResponseBody
    public void updateNotice(@RequestParam("id") Long id, @RequestParam("title") String noticeTitle, @RequestParam("text") String noticeText ) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(id);
        noticeDTO.setNoticeTitle(noticeTitle);
        noticeDTO.setNoticeText(noticeText);
        noticeService.updateNotice(noticeDTO.toVO());
    }

    @GetMapping("admin/notice-add")
    @ResponseBody
    public void addNotice(@RequestParam("title") String noticeTitle, @RequestParam("text") String noticeText) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeTitle(noticeTitle);
        noticeDTO.setNoticeText(noticeText);
        noticeService.insertNotice(noticeDTO.toVO());
    }


}
