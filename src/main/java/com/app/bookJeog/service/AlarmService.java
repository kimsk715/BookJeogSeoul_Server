package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.*;

public interface AlarmService {



    //내 게시글 댓글 알람
    void commentAlarm(Long commentId);


    // 누군가 맨션 알람 등록
    void mentionAlarm(CommentMentionAlarmDTO commentMentionAlarmDTO);


    // 팔로잉한 사람의 게시글 알림
    void postAlarm(PostAlarmDTO postAlarmDTO);


    // 팔로잉 한 사람
    void followAlarm(FollowAlarmDTO followAlarmDTO);


    // 알람 조회







    void readAlarm(Long receiverId);
    int unreadAlarmCount(Long memberId);

    public default CommentAlarmVO toCommentAlarmVO(CommentAlarmDTO commentAlarmDTO) {
       return CommentAlarmVO.builder()
                .id(commentAlarmDTO.getId())
                .commentId(commentAlarmDTO.getCommentId())
                .build();
    };
    public default CommentMentionAlarmVO toCommentMentionAlarmVO(CommentMentionAlarmDTO commentMentionAlarmDTO) {
        return CommentMentionAlarmVO.builder()
                .id(commentMentionAlarmDTO.getId())
                .commentId(commentMentionAlarmDTO.getCommentId())
                .mentionMemberId(commentMentionAlarmDTO.getCommentId())
                .build();
    }
    public default PostAlarmVO toPostAlarmVO(PostAlarmDTO postAlarmDTO) {
        return PostAlarmVO.builder()
                .id(postAlarmDTO.getId())
                .postId(postAlarmDTO.getPostId())
                .build();
    }
    public default FollowAlarmVO toFollowAlarmVO(FollowAlarmDTO followAlarmDTO) {
        return FollowAlarmVO.builder()
                .id(followAlarmDTO.getId())
                .alarmSenderId(followAlarmDTO.getAlarmSenderId())
                .build();
    }
}

