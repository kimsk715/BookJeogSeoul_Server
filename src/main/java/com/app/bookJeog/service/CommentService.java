package com.app.bookJeog.service;

import com.app.bookJeog.domain.vo.CommentVO;

public interface CommentService {
    public void updateCommentStatus(Long id);

    public CommentVO getCommentById(Long id);
}
