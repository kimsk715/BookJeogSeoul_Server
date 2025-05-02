package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.enumeration.AlarmType;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.repository.AlarmDAO;
import com.app.bookJeog.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AlarmServiceImpl implements AlarmService {
    private final AlarmDAO alarmDAO;
    private final AlarmVO alarmVO;
    private final AlarmService alarmService;
    private final CommentAlarmDTO commentAlarmDTO;
    private final MemberDAO memberDAO;

    //댓글 달때 insert
    @Override
    public void commentAlarm(Long commentId) {
        alarmDAO.setAlarm(alarmVO);
        commentAlarmDTO.setId(alarmVO.getId());
        commentAlarmDTO.setCommentId(commentId);

        CommentAlarmVO commentAlarmVO = toCommentAlarmVO(commentAlarmDTO);

        alarmDAO.setCommentAlarm(commentAlarmVO);
    }


    // 누군가 맨션 알람 등록
    @Override
    public void mentionAlarm(CommentMentionAlarmDTO commentMentionAlarmDTO) {
        alarmDAO.setAlarm(alarmVO);
        commentMentionAlarmDTO.setId(commentMentionAlarmDTO.getId());
        commentMentionAlarmDTO.setCommentId(commentMentionAlarmDTO.getCommentId());
        CommentMentionAlarmVO commentMentionAlarmVO = toCommentMentionAlarmVO(commentMentionAlarmDTO);
        alarmDAO.setMentionAlarm(commentMentionAlarmVO);
    }


    // 게시글 알림 등록
    @Override
    public void postAlarm(PostAlarmDTO postAlarmDTO) {
        alarmDAO.setAlarm(alarmVO);

        postAlarmDTO.setId(alarmVO.getId());

        PostAlarmVO postAlarmVO = toPostAlarmVO(postAlarmDTO);
        alarmDAO.setPostAlarm(postAlarmVO);
    }


    // 팔로잉 알림 등록
    @Override
    public void followAlarm(FollowAlarmDTO followAlarmDTO) {
        alarmDAO.setAlarm(alarmVO);

        followAlarmDTO.setId(alarmVO.getId());
        FollowAlarmVO followAlarmVO = toFollowAlarmVO(followAlarmDTO);
        alarmDAO.setFollowAlarm(followAlarmVO);
    }


    // 알림조회



    // 알림 창 들어가면 초기화
    @Override
    public void readAlarm(Long receiverId) {
        alarmDAO.findAlarmRead(receiverId);
    }
    // 알람 카운터갯수
    @Override
    public int unreadAlarmCount(Long memberId) {
       return alarmDAO.findAlarmCount(memberId);
    }



}
