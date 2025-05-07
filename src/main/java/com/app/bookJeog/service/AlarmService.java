package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.AlarmType;
import com.app.bookJeog.domain.vo.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;


public interface AlarmService {

    // 알람들 조회
    AlarmListDTO selectAlarmList(Long memberId);


    //내 게시글 댓글 알람
    void commentAlarm(Long memberId, Long commentId);


    // 누군가 맨션 알람 등록
    void mentionAlarm(Long memberId, CommentMentionAlarmDTO commentMentionAlarmDTO);


    // 팔로잉한 사람의 게시글 알림
    void postAlarm(Long memberId, PostAlarmDTO postAlarmDTO);


    // 팔로잉 한 사람
    void followAlarm(Long memberId, FollowAlarmDTO followAlarmDTO);

    // 알람 조회

    void readAlarm(Long receiverId);
    int unreadAlarmCount(Long memberId);


    // 내 게시물 댓글 enum 포함한거
    public default AlarmVO insertCommentAlarm(AlarmDTO alarmDTO) {
        return AlarmVO.builder()
                .alarmType(AlarmType.COMMENT)
                .alarmReceiverId(alarmDTO.getAlarmReceiverId())
                .id(alarmDTO.getId())
                .build();
    }

    // 멘션 enum 포함한거
    public default AlarmVO insertMentionAlarm(AlarmDTO alarmDTO) {
        return AlarmVO.builder()
                .alarmType(AlarmType.MENTION)
                .alarmReceiverId(alarmDTO.getAlarmReceiverId())
                .id(alarmDTO.getId())
                .build();
    }

    public default AlarmVO toAlarmVO(AlarmDTO alarmDTO) {
        return AlarmVO.builder()
                .id(alarmDTO.getId())
                .alarmReceiverId(alarmDTO.getAlarmReceiverId())
                .alarmType(alarmDTO.getAlarmType())
                .build();
    }
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
                .mentionMemberId(commentMentionAlarmDTO.getMentionMemberId())
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

