package com.app.bookJeog.service;

import com.app.bookJeog.repository.AlarmDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AlarmServiceImpl implements AlarmService {
    private final AlarmDAO alarmDAO;

    @Override
    public void insertCommentAlarm() {
        alarmDAO.setCommentMention();
    }
}
