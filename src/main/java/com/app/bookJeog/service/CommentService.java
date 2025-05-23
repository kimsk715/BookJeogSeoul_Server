package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.CommentDTO;
import com.app.bookJeog.domain.enumeration.CommentStatus;
import com.app.bookJeog.domain.vo.CommentMentionVO;
import com.app.bookJeog.domain.vo.CommentVO;

import java.util.List;

public interface CommentService {
    public void updateCommentStatus(Long id);

    public CommentVO getCommentById(Long id);

    public List<CommentVO> getAllCommentByPostId(Long postId);

    public default CommentDTO toCommentDTO(CommentVO commentVO) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentVO.getId());
        commentDTO.setPostId(commentVO.getPostId());
        commentDTO.setMemberId(commentVO.getMemberId());
        commentDTO.setCommentStatus(commentVO.getCommentStatus());
        commentDTO.setCommentText(commentVO.getCommentText());
        commentDTO.setCreatedDate(commentVO.getCreatedDate());
        commentDTO.setUpdatedDate(commentVO.getUpdatedDate());
        return commentDTO;
    }

    public CommentVO insertComment(CommentVO commentVO);

    public List<CommentVO> getAllMembersByPostId(Long postId);

    public void setMention(CommentMentionVO commentMentionVO);

    public Long getMentionedId(Long commentId);
}
