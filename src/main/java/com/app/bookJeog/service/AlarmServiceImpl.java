package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.AlarmType;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.repository.AlarmDAO;
import com.app.bookJeog.repository.MemberDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AlarmServiceImpl implements AlarmService {
    private final AlarmDAO alarmDAO;

    private final MemberDAO memberDAO;
    private final HttpSession session;

    // 알람들 조회
    @Override
    public AlarmListDTO selectAlarmList(Long memberId) {
        AlarmListDTO alarmListDTOS = new AlarmListDTO();

        alarmListDTOS.setAlarmCommentAlarmDTOS(alarmDAO.findCommentAlarm(memberId));
        alarmListDTOS.setAlarmMentionAlarmDTOS(alarmDAO.findMentionAlarm(memberId));
        alarmListDTOS.setPostAlarmPersonalMemberDTOS(alarmDAO.findPostAlarms(memberId));
        alarmListDTOS.setAlarmFollowAlarmDTOS(alarmDAO.findFollowAlarms(memberId));
        log.info(alarmListDTOS.toString());
        return alarmListDTOS;
    }

    //댓글 달때 insert
    @Override
    public void commentAlarm(Long alarmReceiverId, Long commentId) {
        AlarmVO alarmVO = new AlarmVO();
        AlarmDTO alarmDTO = new AlarmDTO();
        CommentAlarmDTO commentAlarmDTO = new CommentAlarmDTO();


        alarmDTO.setAlarmType(AlarmType.COMMENT);
        alarmDTO.setAlarmReceiverId(alarmReceiverId);
        alarmVO = insertCommentAlarm(alarmDTO);

        //

        alarmDAO.setAlarm(alarmVO);

        commentAlarmDTO.setId(alarmVO.getId());
        commentAlarmDTO.setCommentId(commentId);

        CommentAlarmVO commentAlarmVO = toCommentAlarmVO(commentAlarmDTO);

        alarmDAO.setCommentAlarm(commentAlarmVO);
    }



    // 누군가 맨션 알람 등록
    @Override
    public void mentionAlarm(Long memberId, CommentMentionAlarmDTO commentMentionAlarmDTO) {
        AlarmVO alarmVO = new AlarmVO();
        AlarmDTO alarmDTO = new AlarmDTO();


        alarmDTO.setAlarmReceiverId(memberId);
        alarmVO = insertMentionAlarm(alarmDTO);

        alarmDAO.setAlarm(alarmVO); // 슈퍼키 생성

        commentMentionAlarmDTO.setId(alarmVO.getId()); // 생성된 알람 ID
        CommentMentionAlarmVO vo = toCommentMentionAlarmVO(commentMentionAlarmDTO);
        log.info("commentMentionAlarmVO:{}", vo);
        alarmDAO.setMentionAlarm(vo);
    }



    // 게시글 알림 등록
    @Override
    public void postAlarm(Long memberId, PostAlarmDTO postAlarmDTO) {
        AlarmVO alarmVO = new AlarmVO();
        AlarmDTO alarmDTO = new AlarmDTO();


        alarmDTO.setAlarmType(AlarmType.POST);
        alarmDTO.setAlarmReceiverId(memberId);
        alarmVO = toAlarmVO(alarmDTO);

        alarmDAO.setAlarm(alarmVO);
        postAlarmDTO.setId(alarmVO.getId());

        PostAlarmVO postAlarmVO = toPostAlarmVO(postAlarmDTO);
        alarmDAO.setPostAlarm(postAlarmVO);
    }


    // 팔로잉 알림 등록
    @Override
    public void followAlarm(Long memberId, FollowAlarmDTO followAlarmDTO) {
        AlarmVO alarmVO = new AlarmVO();
        AlarmDTO alarmDTO = new AlarmDTO();


        alarmDTO.setAlarmType(AlarmType.FOLLOW);
        alarmDTO.setAlarmReceiverId(memberId);
        alarmVO = toAlarmVO(alarmDTO);

        alarmDAO.setAlarm(alarmVO);

        followAlarmDTO.setId(alarmVO.getId());
        FollowAlarmVO followAlarmVO = toFollowAlarmVO(followAlarmDTO);
        alarmDAO.setFollowAlarm(followAlarmVO);
    }





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
