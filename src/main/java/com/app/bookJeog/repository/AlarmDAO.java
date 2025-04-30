package com.app.bookJeog.repository;

import com.app.bookJeog.mapper.AlarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlarmDAO {

    private final AlarmMapper alarmMapper;

    // 댓글알람
    public void setCommentMention(){
        alarmMapper.insertCommentMention();
    }
}
