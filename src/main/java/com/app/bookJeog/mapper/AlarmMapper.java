package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.MyAlarmDTO;
import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AlarmMapper {


    //알람 슈퍼키
    public void insertAlarm(AlarmVO alarmVO);


    // 내가 작성한 게시글 등록
    public void insertCommentAlarm(CommentAlarmVO commentAlarmVO);


    // 누군가 맨션 알람 등록
    public void insertMentionAlarm(CommentMentionAlarmVO commentMentionAlarmVO);


    // 팔로잉한 사람의 게시글 알림
    public void insertPostAlarm(PostAlarmVO postAlarmVO);


    // 팔로잉 한 사람
    public void insertFollowAlarm(FollowAlarmVO followAlarmVO);


    // 내가 작성한 게시글 답글 조회
    public Optional<MyAlarmDTO> selectMyPostOnlyComment();


    // 안읽은 알람 갯수
    public int selectAlarmCount(Long memberId);


    // 읽은 알람
    public void updateAlarmRead(Long receiverId);






}
