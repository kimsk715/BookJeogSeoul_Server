package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.mapper.AlarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AlarmDAO {

    private final AlarmMapper alarmMapper;

    // 알람 슈퍼키 등록
    public void setAlarm(AlarmVO alarmVO) {
        alarmMapper.insertAlarm(alarmVO);
    }


    // 내가 작성한 게시글 댓글 알람 등록
    public void setCommentAlarm(CommentAlarmVO commentAlarmVO)  {
        alarmMapper.insertCommentAlarm(commentAlarmVO);
    }


    // 누군가 맨션 알람 등록
    public void setMentionAlarm(CommentMentionAlarmVO commentMentionAlarmVO)  {
        alarmMapper.insertMentionAlarm(commentMentionAlarmVO);
    }


    // 팔로잉한 사람의 게시글 알림
    public void setPostAlarm(PostAlarmVO postAlarmVO)  {
        alarmMapper.insertPostAlarm(postAlarmVO);
    }


    // 팔로잉 한 사람
    public void setFollowAlarm(FollowAlarmVO followAlarmVO)  {
        alarmMapper.insertFollowAlarm(followAlarmVO);
    }


    // 내 게시글 답글 조회
    public Optional<MyAlarmDTO> findMyPostOnlyComment() {
        return alarmMapper.selectMyPostOnlyComment();
    };


    // 안읽은 알람 카운터
    public int findAlarmCount(Long memberId) {
        return alarmMapper.selectAlarmCount(memberId);
    }


    // 읽은 알람
    public void findAlarmRead(Long receiverId) {
        alarmMapper.updateAlarmRead(receiverId);
    }


    // 팔로우 조회
    public List<AlarmFollowAlarmDTO> findFollowAlarms(Long memberId) {
        return alarmMapper.selectFollowAlarms(memberId);
    }


    // 게시글에 댓글 조회
    public List<AlarmCommentAlarmDTO> findCommentAlarm(Long memberId) {
        return alarmMapper.selectCommentAlarm(memberId);
    }


    // 멘션 조회
    public List<AlarmMentionAlarmDTO> findMentionAlarm(Long memberId) {
        return alarmMapper.selectMention(memberId);
    }


    // 팔로우한사람의 게시글 조회
    public List<PostAlarmPersonalMemberDTO> findPostAlarms(Long memberId) {
        return alarmMapper.selectPostAlarms(memberId);
    }

}
