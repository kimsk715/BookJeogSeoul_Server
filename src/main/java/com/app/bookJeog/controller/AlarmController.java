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
    private final AlarmDTO alarmDTO;

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

        alarmService.readAlarm(foundMember.getId());


        List<String> paths = new ArrayList<>();
        Long memberId = foundMember.getId();
        AlarmListDTO alarmListDTO =  alarmService.selectAlarmList(memberId);

        List<AlarmFollowAlarmDTO> alarmFollowAlarmDTOList = alarmListDTO.getAlarmFollowAlarmDTOS();
        List<AlarmCommentAlarmDTO> commentAlarmDTOList = alarmListDTO.getAlarmCommentAlarmDTOS();
        List<AlarmMentionAlarmDTO> mentionAlarmDTOList = alarmListDTO.getAlarmMentionAlarmDTOS();
        List<PostAlarmPersonalMemberDTO> postAlarmPersonalMemberDTOList = alarmListDTO.getPostAlarmPersonalMemberDTOS();
        List<CommentAlarmInfoDTO> commentAlarmInfoDTOS = new ArrayList<>();
        String followAlarmPath = "/personal/mypage/";

        for (AlarmFollowAlarmDTO alarmFollowAlarmDTO : alarmFollowAlarmDTOList) {
            FollowMemberAlarmDTO followMemberAlarmDTO = new FollowMemberAlarmDTO();

            followMemberAlarmDTO.setMemberNickName(alarmFollowAlarmDTO.getMemberNickName());

            Long senderId = alarmFollowAlarmDTO.getAlarmSenderId();
            followAlarmPath += senderId;

            followMemberAlarmDTO.setFollowPath(followAlarmPath);
        }

        model.addAttribute("followAlarmPath", followAlarmPath);


        for(AlarmCommentAlarmDTO commentAlarmDTO : commentAlarmDTOList){
            CommentAlarmInfoDTO commentAlarmInfoDTO = new CommentAlarmInfoDTO();

           Long postId = commentAlarmDTO.getPostId();

            String path = postService.getPostType(postId);
            path += postId;
            commentAlarmInfoDTO.setCommentPath(path);
            commentAlarmInfoDTO.setMemberNickname(commentAlarmDTO.getMemberNickname());
            commentAlarmInfoDTOS.add(commentAlarmInfoDTO);
        }
        model.addAttribute("commentAlarms", commentAlarmInfoDTOS);

        List<MentionAlarmInfoDTO> mentionAlarmInfoDTOS = new ArrayList<>();
        for(AlarmMentionAlarmDTO mentionAlarmDTO : mentionAlarmDTOList){
            MentionAlarmInfoDTO mentionAlarmInfoDTO = new MentionAlarmInfoDTO();
            Long postId = mentionAlarmDTO.getPostId();
            String mentionPath = postService.getPostType(postId);
            mentionPath += postId;
            mentionAlarmInfoDTO.setMentionedPath(mentionPath);
            mentionAlarmInfoDTO.setMemberNickname(mentionAlarmDTO.getMemberNickname());
            mentionAlarmInfoDTOS.add(mentionAlarmInfoDTO);
        }

        model.addAttribute("mentionAlarms", mentionAlarmInfoDTOS);

        List<String> followPaths = new ArrayList<>();
        List<PostAlarmInfoDTO> postAlarmInfoDTOS = new ArrayList<>();
        for(PostAlarmPersonalMemberDTO postAlarmPersonalMemberDTO : postAlarmPersonalMemberDTOList){
            PostAlarmInfoDTO postAlarmInfoDTO = new PostAlarmInfoDTO();
            Long postId = postAlarmPersonalMemberDTO.getPostId();
            String followPath = postService.getPostType(postId);
            followPath += postId;
            postAlarmInfoDTO.setPostPath(followPath);
            postAlarmInfoDTO.setMemberNickname(postAlarmPersonalMemberDTO.getMemberNickName());
            postAlarmInfoDTOS.add(postAlarmInfoDTO);
        }

        List<String> postPaths = new ArrayList<>();
        model.addAttribute("postAlarms", postAlarmInfoDTOS);

        for(PostAlarmPersonalMemberDTO postAlarmPersonalMemberDTO : postAlarmPersonalMemberDTOList){
            Long postId = postAlarmPersonalMemberDTO.getPostId();
            String postPath = postService.getPostType(postId);
            postPath += postId;
            postAlarmInfoDTOS.forEach(postAlarmInfoDTO -> {
//                postAlarmInfoDTO.setMemberName(alarmService.);
            });
            postPaths.add(postPath);

        }
        model.addAttribute("postAlarms", postAlarmInfoDTOS);

//        for(PostAlarmPersonalMemberDTO postAlarmPersonalMemberDTO : postAlarmPersonalMemberDTOList){
//            Long postId = postAlarmPersonalMemberDTO.getPostId();
//            String postPath = postService.getPostType(postId);
//            postPath += postId;
//            postAlarmInfoDTOS.forEach(postAlarmInfoDTO -> {
////                postAlarmInfoDTO.setMemberName(alarmService.);
//            });
//            postPaths.add(postPath);
//
//        }
//        log.info(postAlarmInfoDTOS.toString());



        return "main/alarm";
    }
}
