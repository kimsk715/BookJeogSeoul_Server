package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.HistoryDTO;
import com.app.bookJeog.domain.vo.HistoryVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class HistoryMapperTests {
    @Autowired
    HistoryMapper historyMapper;

    // 도서 조회기록, 줄거리 저장
    @Test
    public void testInsertHistory() {
        HistoryDTO historyDTO = new HistoryDTO();

        historyDTO.setMemberId(1L);
        historyDTO.setBookIsbn(9788984374423L);
        historyDTO.setBookSummary("샘과 줄리에트, 그레이스와 마크 루텔리는 사랑을 지키기 위해 주어진 운명을 거부하고 현실과 싸워나간다. 화해와 용서는 어둠을 극복하고 밝고 환한 세계로 나아가고자 하는 그들이 가장 우선적으로 해결해야 할 숙제이다.");

        HistoryVO historyVO = historyDTO.toVO();

        int count = historyMapper.countTodayHistory(historyVO);

        if (count == 0) {
            historyMapper.insertHistory(historyVO);
            log.info("✅ 오늘 처음 조회한 책이라 INSERT 완료");
        } else {
            log.info("❌ 오늘 이미 본 책이므로 INSERT 생략");
        }
    }
}
