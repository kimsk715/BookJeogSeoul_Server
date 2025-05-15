package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.NoticeVO;
import com.app.bookJeog.service.FIleService;
import com.app.bookJeog.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final FIleService fileService;
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
    public NoticeInfoDTO getNotice(@RequestParam("id") Long id) {
        NoticeVO noticeVO = noticeService.selectNoticeById(id);
        List<FileDTO> fileList = fileService.getNoticeFilesByNoticeId(noticeVO.getId());
        NoticeInfoDTO noticeInfoDTO = new NoticeInfoDTO();
        noticeInfoDTO.setNoticeVO(noticeVO);
        noticeInfoDTO.setFileList(fileList);
        return noticeInfoDTO;
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




    @PostMapping("admin/notice-add")
    @ResponseBody
    public Map<String, Object> addNotice(
            @RequestParam("title") String noticeTitle,
            @RequestParam("text") String noticeText,
            @RequestParam("files") List<MultipartFile> files) {

        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeTitle(noticeTitle);
        noticeDTO.setNoticeText(noticeText);
        log.info(files.toString());
        NoticeVO noticeVO = noticeDTO.toVO();
        noticeService.insertNotice(noticeVO);
        Long noticeId = noticeVO.getId();
        log.info(noticeId.toString());
        fileService.uploadNoticeFiles(noticeId, files);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}
