package com.app.bookJeog.controller;

import com.app.bookJeog.controller.member.MemberControllerDocs;
import com.app.bookJeog.domain.dto.CommentDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.service.CommentService;
import com.app.bookJeog.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class CommentController implements MemberControllerDocs {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("post-comment")
    @ResponseBody
    public void postComment(@RequestParam("id")Long postId, @RequestParam("text") String commentText, HttpSession session) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPostId(postId);
        commentDTO.setCommentText(commentText);
        if(session.getAttribute("member") != null){
            Optional<PersonalMemberDTO> foundPersonal = (Optional<PersonalMemberDTO>) session.getAttribute("member");
            PersonalMemberDTO foundMember = foundPersonal.orElseThrow(RuntimeException::new);
            commentDTO.setMemberId(foundMember.getId());
        }


        commentService.insertComment(commentDTO.toVO());
    }
}
