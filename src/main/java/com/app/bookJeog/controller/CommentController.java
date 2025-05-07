package com.app.bookJeog.controller;

import com.app.bookJeog.controller.member.MemberControllerDocs;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.dto.CommentDTO;
import com.app.bookJeog.domain.dto.CommentMentionAlarmDTO;
import com.app.bookJeog.domain.dto.CommentMentionDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.dto.SponsorMemberDTO;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.CommentMentionVO;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.service.*;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.service.CommentService;
import com.app.bookJeog.service.MemberService;
import com.app.bookJeog.service.PostService;
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
    private final AlarmService alarmService;
    private final PostService postService;


    @GetMapping("post-comment")
    @ResponseBody
    public void postComment(@RequestParam("id")Long postId, @RequestParam("text") String commentText, @RequestParam(value = "mention-id",required = false) Long mentionId, HttpSession session, Model model) {
        AlarmDTO alarmDTO = new AlarmDTO();
        CommentDTO commentDTO = new CommentDTO();

        // 게시글 로 memberId 가져오기
        Optional<PostVO> optionalPostVO = postService.selectMemberIdByPostId(postId);
        PostVO postVO = optionalPostVO.orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Long receiverId = postVO.getMemberId();

        commentDTO.setPostId(postId);
        commentDTO.setCommentText(commentText);
        if (session.getAttribute("member") != null) {
            PersonalMemberDTO foundMember = (PersonalMemberDTO) session.getAttribute("member");
            commentDTO.setMemberId(foundMember.getId());
        }
        else if(session.getAttribute("sponsorMember") != null){
            SponsorMemberVO foundSponsorMember = (SponsorMemberVO) session.getAttribute("sponsorMember");
            commentDTO.setMemberId(foundSponsorMember.getId());
        }
        else{
            throw new RuntimeException();
        }
        CommentVO commentVO = commentDTO.toVO();
        commentService.insertComment(commentVO);
        CommentMentionAlarmDTO commentMentionAlarmDTO = new CommentMentionAlarmDTO();
        Object foundMember = null;
        if (mentionId != null) {
            CommentMentionDTO commentMentionDTO = new CommentMentionDTO();
            commentMentionDTO.setCommentId(commentVO.getId());
            commentMentionDTO.setMentionedMemberId(mentionId);
            log.info("commentMentionDTO: {}", commentMentionDTO);
            commentService.setMention(commentMentionDTO.toVO());

            commentMentionAlarmDTO.setCommentId(commentVO.getId());
            commentMentionAlarmDTO.setMentionMemberId(commentMentionDTO.getMentionedMemberId());
            log.info("commentMentionAlarmDTO:{}", commentMentionAlarmDTO);

            foundMember = session.getAttribute("member");
            alarmService.mentionAlarm(commentMentionDTO.getMentionedMemberId(), commentMentionAlarmDTO);
        } else {
            alarmService.commentAlarm(receiverId, commentVO.getId());
        }
    }
}
