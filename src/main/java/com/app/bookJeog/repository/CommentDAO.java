package com.app.bookJeog.repository;

import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDAO {
    private final CommentMapper commentMapper;

    public CommentVO findCommentById(Long commentId) {
        return commentMapper.selectCommentById(commentId);
    }

    public void updateCommentStatus(Long id) {
        commentMapper.updateCommentStatus(id);
    }

    public int countAllCommentByPostId(Long postId) {
        return commentMapper.countAllCommentByPostId(postId);
    }
    public List<CommentVO> findAllCommentByPostId(Long postId) {
        return commentMapper.selectAllCommentByPostId(postId);
    }

    public void insertCommentByPostId(CommentVO commentVO) {
        commentMapper.insertCommentByPostId(commentVO);
    }
}
