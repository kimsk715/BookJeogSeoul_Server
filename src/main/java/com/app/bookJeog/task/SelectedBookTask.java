package com.app.bookJeog.task;

import com.app.bookJeog.domain.dto.MonthlyBookPostDTO;
import com.app.bookJeog.domain.dto.SelectedBookDTO;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import com.app.bookJeog.service.AladinService;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.EventType;
import com.app.bookJeog.service.NoticeService;
import com.app.bookJeog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SelectedBookTask {
    private final BookService bookService;
    private final PostService postService;
    private final AladinService aladinService;
    private final NoticeService noticeService;

    /*
     *   0 * * * * * : 매 분 0초마다
     *   0/30 * * * * * : 매 30초 간격
     *   0 0/1 * * * * : 매 1분 간격
     *   0 0/5 * ? : 매 5분 간격
     *   0 0 0/1 * * * : 매 1시간 간격
     *   0 0 0 * * ? : 매일 0시 마다
     *   0 0 0 1 * ? : 매월 1일 마다
     *   * 10-13 * * * * : 매 10, 11, 12, 13분에 동작한다.
     * */
// 매 달 1일 마다 임시 선정 도서를 실제 선정 도서로
    @Scheduled(cron = "0 0 0 1 * ?")
    public void insertSelectedBook() {
        List<TempSelectedBookVO> tempList = bookService.getTempSelectedBook();
        log.info(tempList.toString());
        List<SelectedBookVO> selectedList = new ArrayList<>();
        for( TempSelectedBookVO temp : tempList ) {
            SelectedBookDTO selectedDTO = new SelectedBookDTO();
            selectedDTO.setId(temp.getId());
            selectedDTO.setBookIsbn(temp.getBookIsbn());
            selectedDTO.setBookImageUrl("'" + aladinService.getBookCover(temp.getBookIsbn()) + "'"); // 임시 테스트용 여기 자리에 표지 이미지 가져올 수 있는 API 연결.
            SelectedBookVO selectedVO = selectedDTO.toSelectedBookVO();
            log.info(selectedVO.toString());
            selectedList.add(selectedVO);
        }
        log.info(selectedList.toString());

        selectedList.forEach(bookService::insertSelectedBook);
    }

//    매달 20 일에 좋아요 수를 기준으로 이 달의 독후감 후보 선정
    @Scheduled(cron = "0 0 0 20 * ?")
    public void insertTopPosts() {
        List<BookPostVO> tempList = postService.getTopPosts();
        log.info(tempList.toString());
        List<MonthlyBookPostDTO> monthlyList = new ArrayList<>();
        for( BookPostVO post : tempList ) {
            MonthlyBookPostDTO monthlyDTO = new MonthlyBookPostDTO();
            monthlyDTO.setBookPostId(post.getId());
            monthlyDTO.setBookPostTitle(post.getBookPostTitle());
            monthlyDTO.setBookPostText(post.getBookPostText());
            monthlyDTO.setBookPostLikeCount(post.getBookPostLikeCount());
            monthlyDTO.setBookPostVoteCount(post.getBookPostVoteCount());
            monthlyList.add(monthlyDTO);
        }
        log.info(monthlyList.toString());

        List<MonthlyBookPostVO> monthlyVOList = new ArrayList<>();
        for (MonthlyBookPostDTO monthly : monthlyList) {
            MonthlyBookPostVO monthlyVO = monthly.toMonthlyBookPostVO();
            monthlyVOList.add(monthlyVO);
        }

        log.info(monthlyVOList.toString());

        monthlyVOList.forEach(postService::insertTopBookPosts);
    }

//   투표 결과 1등인 독후감 저장.
    @Scheduled(cron = "0 0 12 30 * ?")
    public void insertTopBookPost() {
        Optional<MonthlyBookPostVO> topPost = postService.getBestPost();
        log.info(topPost.toString());
        MonthlyBookPostVO foundTopPost = topPost.orElse(null);
        log.info(foundTopPost.toString());
        postService.insertBestPost(foundTopPost);
    }

    // 투표 후보 선정(tbl_event)
    @Scheduled(cron = "10 0 0 20 * ?")
    public void insertVoteEvent(){
        EventDTO eventDTO = new EventDTO();
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear() % 100;
        int month = localDate.getMonthValue();
        eventDTO.setYear(year);
        eventDTO.setMonth(month);
        eventDTO.setEventType(EventType.VOTE);
        String message = "20" + String.valueOf(year) + "년 " + String.valueOf(month) + "월 이 달의 독후감 투표입니다.";
        eventDTO.setEventText(message);
        noticeService.setEvent(eventDTO.toVO());
    }

    // 투표 결과 발표(tbl_event)
    // (25년 7월에 생성된 데이터라면, 25년 6월의 선정 결과이므로
    // month 의 값을 1 빼주었음.)
    @Scheduled(cron = "1 0 0 1 * ?")
    public void insertResultEvent(){
        EventDTO eventDTO = new EventDTO();
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear() % 100;
        int month = localDate.getMonthValue()-1;
        eventDTO.setYear(year);
        eventDTO.setMonth(month);
        eventDTO.setEventType(EventType.RESULT);
        String message = "20" + String.valueOf(year) + "년 " + String.valueOf(month) + "월 이 달의 독후감 선정 결과입니다.";
        eventDTO.setEventText(message);
        noticeService.setEvent(eventDTO.toVO());
    }
}
