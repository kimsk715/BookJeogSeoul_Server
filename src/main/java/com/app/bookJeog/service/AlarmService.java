package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.AdminDTO;
import com.app.bookJeog.domain.dto.AlarmDTO;
import com.app.bookJeog.domain.vo.AdminVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AlarmService {

    // 댓글 알람
    public void insertCommentAlarm();
}
