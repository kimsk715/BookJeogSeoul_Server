package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.HistoryDTO;
import com.app.bookJeog.domain.vo.HistoryVO;
import com.app.bookJeog.repository.HistoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class HistoryServiceImpl implements HistoryService {
    private final HistoryDAO historyDAO;

    // 조회한 책의 오늘 데이터가 있는지 검사 후 없으면 저장
    public void saveHistoryIfNotExists(HistoryDTO historyDTO) {
        HistoryVO historyVO = historyDTO.toVO();
        if(historyDAO.countTodayHistory(historyVO) == 0){
            historyDAO.insertHistory(historyVO);
        }
    }
}
