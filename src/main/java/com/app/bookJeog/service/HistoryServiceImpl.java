package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.HistoryDTO;
import com.app.bookJeog.domain.vo.HistoryVO;
import com.app.bookJeog.repository.HistoryDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private final HistoryDAO historyDAO;

    // 조회한 책의 오늘 데이터가 있는지 검사 후 없으면 저장
    public void saveHistoryIfNotExists(HistoryDTO historyDTO) {
        HistoryVO historyVO = historyDTO.toVO();
        int count = historyDAO.countTodayHistory(historyVO);

        if(count == 0){
            historyDAO.insertHistory(historyVO);
            log.info("히스토리 저장 완료 - memberId: {}, isbn: {}", historyVO.getMemberId(), historyVO.getBookIsbn());
        } else{
            log.info("오늘 이미 조회된 책 - 저장 생략됨 (memberId: {}, isbn: {})", historyVO.getMemberId(), historyVO.getBookIsbn());
        }
    }
}
