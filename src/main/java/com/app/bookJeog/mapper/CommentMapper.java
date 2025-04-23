package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    public CommentVO selectCommentById(Long id);

    public void updateCommentStatus(Long id);
}
