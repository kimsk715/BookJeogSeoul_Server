package com.app.bookJeog.repository;

import com.app.bookJeog.domain.vo.HistoryVO;
import com.app.bookJeog.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HistoryDAO {
    private final HistoryMapper historyMapper;

    // 조회한 책의 오늘 데이터가 있는지 검사
    public int countTodayHistory(HistoryVO historyVO){
        return historyMapper.countTodayHistory(historyVO);
    };

    // 상세페이지를 간 도서의 정보, 줄거리 저장
    public void insertHistory(HistoryVO historyVO){
        historyMapper.insertHistory(historyVO);
    };
}
