package com.app.bookJeog.controller;

import com.app.bookJeog.controller.member.MemberControllerDocs;
import com.app.bookJeog.domain.dto.CommentDTO;
import com.app.bookJeog.domain.dto.CommentMentionAlarmDTO;
import com.app.bookJeog.domain.dto.CommentMentionDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.CommentMentionVO;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class CommentController implements MemberControllerDocs {
    private final CommentService commentService;
    private final AlarmServiceImpl alarmServiceImpl;
    private final AlarmService alarmService;
    private final CommentMentionAlarmDTO commentMentionAlarmDTO;

    @GetMapping("post-comment")
    @ResponseBody
    public void postComment(@RequestParam("id")Long postId, @RequestParam("text") String commentText, @RequestParam(value = "mention-id",required = false) Long mentionId, HttpSession session, Model model) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPostId(postId);
        commentDTO.setCommentText(commentText);
        if(session.getAttribute("member") != null){
            PersonalMemberDTO foundMember = (PersonalMemberDTO) session.getAttribute("member");
            commentDTO.setMemberId(foundMember.getId());

        }
        CommentVO commentVO = commentDTO.toVO();
        commentService.insertComment(commentVO);
//        log.info(commentVO.toString());

        if(mentionId != null){
            CommentMentionDTO mentionDTO = new CommentMentionDTO();
            mentionDTO.setCommentId(commentVO.getId());
            mentionDTO.setMentionMemberId(mentionId);
            commentService.setMention(mentionDTO.toVO());
            commentMentionAlarmDTO.setId(mentionDTO.getId());
            alarmService.mentionAlarm(commentMentionAlarmDTO);
        }else {
            alarmService.commentAlarm(commentVO.getId());
        }

    }
}
