package com.app.bookJeog.task;

import com.app.bookJeog.domain.dto.SelectedBookDTO;
import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import com.app.bookJeog.service.BookService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class SelectedBookTask {
    private final BookService bookService;
    /*
     *   0 * * * * * : 매 분 0초마다
     *   0/1 * * * * : 매 1초 간격
     *   0 0/1 * * * : 매 1분 간격
     *   0 0/5 * ? : 매 5분 간격
     *   0 0 0/1 * * : 매 1시간 간격
     *   0 0 0 * * ? : 매일 0시 마다
     *   0 0 0 1 * ? : 매월 1일 마다
     *   * 10-13 * * * * : 매 10, 11, 12, 13분에 동작한다.
     * */

    @Scheduled(cron = "0 0 0 1 * *")
    public void insertSelectedBook() {
        List<TempSelectedBookVO> tempList = bookService.getTempSelectedBook();
        log.info(tempList.toString());
        List<SelectedBookVO> selectedList = new ArrayList<>();
        for( TempSelectedBookVO temp : tempList ) {
            SelectedBookDTO selectedDTO = new SelectedBookDTO();
            selectedDTO.setId(temp.getId());
            selectedDTO.setBookIsbn(temp.getBookIsbn());
            selectedDTO.setBookImageUrl("1"); // 임시 테스트용
            SelectedBookVO selectedVO = selectedDTO.toSelectedBookVO();
            log.info(selectedVO.toString());
            selectedList.add(selectedVO);
        }
        log.info(selectedList.toString());

        selectedList.forEach(bookService::insertSelectedBook);


    }
}
