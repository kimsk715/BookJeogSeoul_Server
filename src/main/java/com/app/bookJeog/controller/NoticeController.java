package com.app.bookJeog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.app.bookJeog.domain.dto.EventDTO;
import com.app.bookJeog.domain.dto.NoticeInfoDTO;
import com.app.bookJeog.domain.enumeration.EventType;
import com.app.bookJeog.domain.vo.BestBookPostVO;
import com.app.bookJeog.service.FIleService;
import com.app.bookJeog.service.MemberService;
import com.app.bookJeog.service.NoticeService;
import com.app.bookJeog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class NoticeController  {

        @GetMapping("/notice")
    public String goToNoticeList(){
        return "main/notice_event";
    }
    private final NoticeService noticeService;
    private final FIleService fileService;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/notice")
    public String goToNoticeList(Model model){
        List<NoticeInfoDTO> noticeList =  noticeService.getAllNoticeClient();
        for(NoticeInfoDTO noticeInfoDTO : noticeList){
            Long noticeId = noticeInfoDTO.getNoticeVO().getId();
            noticeInfoDTO.setFileList(fileService.getNoticeFilesByNoticeId(noticeId));
        }
        model.addAttribute("noticeList", noticeList);
        List<EventDTO> eventList =  noticeService.getAllEvent();
        model.addAttribute("eventList", eventList);
        return "main/notice_event";
    }

    @GetMapping("/event/detail/{id}")
    public String goToEventDetail(Model model, @PathVariable Long id){
        EventDTO eventDTO = noticeService.getEventById(id);
        String date = "";
        if(eventDTO.getMonth() < 10){
            date = String.valueOf(eventDTO.getYear())+"0"+String.valueOf(eventDTO.getMonth());
        }
        else{
            date = String.valueOf(eventDTO.getMonth())+String.valueOf(eventDTO.getYear());
        }
        model.addAttribute("posts",postService.getMonthlyBookPosts(date));

         if(eventDTO.getEventType() == EventType.RESULT){

             BestBookPostVO bestPost = postService.getBestBookPostByDate(date);
             model.addAttribute("bestPost", bestPost);
             Long memberId = postService.getBookPostById(bestPost.getBookPostId()).getMemberId();
             String memberName = memberService.getMemberName(memberId);
             model.addAttribute("memberName", memberName);
         }
        model.addAttribute("event",eventDTO);
        return "main/event_content";
    }
}
