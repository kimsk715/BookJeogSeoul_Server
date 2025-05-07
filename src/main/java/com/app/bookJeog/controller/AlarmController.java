package com.app.bookJeog.controller;

import com.app.bookJeog.controller.member.MemberControllerDocs;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.service.AlarmService;
import com.app.bookJeog.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;
    private final PostService postService;

    // 알림 센터
    @GetMapping("/alarm-info")
    @ResponseBody
    public String goToAlarm(HttpSession session) {
//        PersonalMemberDTO foundMember = (PersonalMemberDTO) session.getAttribute("member");

//        return alarmService.selectAlarmList(foundMember.getId());
        return "/main/alarm";
    }

    @GetMapping("post-alarm")
    public String setPath(HttpSession session, Model model) {
        PersonalMemberDTO foundMember = (PersonalMemberDTO) session.getAttribute("member");
        List<String> paths = new ArrayList<>();
        Long memberId = foundMember.getId();
        AlarmListDTO alarmListDTO =  alarmService.selectAlarmList(memberId);

        List<AlarmCommentAlarmDTO> commentAlarmDTOList = alarmListDTO.getAlarmCommentAlarmDTOS();
        List<AlarmMentionAlarmDTO> mentionAlarmDTOList = alarmListDTO.getAlarmMentionAlarmDTOS();
        List<PostAlarmPersonalMemberDTO> postAlarmPersonalMemberDTOList = alarmListDTO.getPostAlarmPersonalMemberDTOS();

        for(AlarmCommentAlarmDTO commentAlarmDTO : commentAlarmDTOList){
           Long postId = commentAlarmDTO.getPostId();

            String path = postService.getPostType(postId);
            path += postId;
            paths.add(path);
        }
        log.info("paths: " + paths);
        List<String> mentionedPaths = new ArrayList<>();
        for(AlarmMentionAlarmDTO mentionAlarmDTO : mentionAlarmDTOList){
            Long postId = mentionAlarmDTO.getPostId();
            String mentionPath = postService.getPostType(postId);
            mentionPath += postId;
            mentionedPaths.add(mentionPath);
        }

        List<String> followPaths = new ArrayList<>();
        for(PostAlarmPersonalMemberDTO postAlarmPersonalMemberDTO : postAlarmPersonalMemberDTOList){
            Long postId = postAlarmPersonalMemberDTO.getPostId();
            String followPath = postService.getPostType(postId);
            followPath += postId;
            followPaths.add(followPath);
        }

        List<String> postPaths = new ArrayList<>();
        for(PostAlarmPersonalMemberDTO postAlarmPersonalMemberDTO : postAlarmPersonalMemberDTOList){
            Long postId = postAlarmPersonalMemberDTO.getPostId();
            String postPath = postService.getPostType(postId);
            postPath += postId;
            postPaths.add(postPath);
            postPath.add()
        }



        model.addAttribute("paths", paths);
        model.addAttribute("mentionedPaths", mentionedPaths);
        model.addAttribute("followPaths", followPaths);
        model.addAttribute("postPaths", postPaths);
        return "main/alarm";
    }
}
