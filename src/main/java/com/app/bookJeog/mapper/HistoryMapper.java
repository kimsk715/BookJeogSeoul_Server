package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.HistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HistoryMapper {

    // 조회한 책의 오늘 데이터가 있는지 검사
    int countTodayHistory(HistoryVO historyVO);

    // 상세페이지를 간 도서의 정보, 줄거리 저장
    void insertHistory(HistoryVO historyVO);
}
