package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.CommentDTO;
import com.app.bookJeog.domain.vo.CommentMentionVO;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.mapper.CommentMapper;
import com.app.bookJeog.repository.CommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

    @Override
    public void updateCommentStatus(Long id) {
        commentDAO.updateCommentStatus(id);
    }

    @Override
    public CommentVO getCommentById(Long id) {
        return commentDAO.findCommentById(id);
    }

    @Override
    public List<CommentVO> getAllCommentByPostId(Long postId) {
        return commentDAO.findAllCommentByPostId(postId);
    }

    @Override
    public CommentVO insertComment(CommentVO commentVO) {
        commentDAO.insertCommentByPostId(commentVO);
        return commentVO;
    }

    @Override
    public List<CommentVO> getAllMembersByPostId(Long postId) {
        return commentDAO.findAllMembersByPostId(postId);
    }

    @Override
    public void setMention(CommentMentionVO commentMentionVO) {
        commentDAO.setMention(commentMentionVO);
    }


}
