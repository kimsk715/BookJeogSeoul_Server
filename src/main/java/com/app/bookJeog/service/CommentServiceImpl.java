package com.app.bookJeog.service;

import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.mapper.CommentMapper;
import com.app.bookJeog.repository.CommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
