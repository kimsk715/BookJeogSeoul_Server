package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.NoticeInfoDTO;
import com.app.bookJeog.service.FIleService;
import com.app.bookJeog.service.NoticeService;
import com.app.bookJeog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class NoticeController  {

    private final NoticeService noticeService;
    private final FIleService fileService;
    private final PostService postService;

    @GetMapping("/notice")
    public String goToNoticeList(Model model){
        List<NoticeInfoDTO> noticeList =  noticeService.getAllNoticeClient();
        for(NoticeInfoDTO noticeInfoDTO : noticeList){
            Long noticeId = noticeInfoDTO.getNoticeVO().getId();
            noticeInfoDTO.setFileList(fileService.getNoticeFilesByNoticeId(noticeId));
        }
        model.addAttribute("noticeList", noticeList);

        return "main/notice_event";
    }

    @GetMapping("/event/detail")
    public String goToEventDetail(Model model){
         model.addAttribute("posts",postService.getMonthlyBookPosts());
        return "main/event_content";
    }
}
