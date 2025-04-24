package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.HistoryDTO;
import com.app.bookJeog.domain.vo.HistoryVO;

public interface HistoryService {

    // 조회한 책의 오늘 데이터가 있는지 검사 후 없으면 저장
    public void saveHistoryIfNotExists(HistoryDTO historyDTO);
}
