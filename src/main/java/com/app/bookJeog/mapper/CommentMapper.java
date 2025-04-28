package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.CommentMentionVO;
import com.app.bookJeog.domain.vo.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public CommentVO selectCommentById(Long id);

    public void updateCommentStatus(Long id);

    public int countAllCommentByPostId(Long postId);

    public List<CommentVO> selectAllCommentByPostId(Long postId);

    public void insertCommentByPostId(CommentVO commentVO);

    public List<CommentVO> selectAllMembersByPostId(Long postId);

    public void insertMention(CommentMentionVO commentMentionVO);
}
