package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.EventDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.enumeration.EventType;
import com.app.bookJeog.domain.vo.ChatGPTRequest;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.repository.PostDAO;
import com.app.bookJeog.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

// mapper 말고 서비스 계층에서 테스트할 것들 모아놓음. 여러 DAO 섞여있음.
@SpringBootTest
@Slf4j
public class ETCTests {

    @Autowired
    private ReportService reportService;

    @Autowired
    private BookService bookService;

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PostService postService;
    @Autowired
    private NoticeService noticeService;


    //  댓글 신고 조회 테스트
    @Test
    public void test(){
        Pagination pagination = new Pagination();
        pagination.create(20);

        log.info(reportService.getAllCommentReport(pagination).toString());


    }

    @Test
    public void test2(){
        PostVO postVO = postDAO.findPostById(1010L);
        log.info(postVO.toString());
    }

    @Test
    public void test3(){
        log.info(postService.getReceiverPosts().toString());
    }

    @Test
    public void test4(){
        log.info(bookService.getBookByIsbn(9788993968460L).toString());
    }

    @Test
    public void openAITest(){
        log.info(noticeService.getAllNoticeClient().toString());
    }

    @Test
    public void insertEvent(){
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

    @Test
    public void insertEvent2(){
        EventDTO eventDTO = new EventDTO();
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear() % 100;
        int month = localDate.getMonthValue();
        eventDTO.setYear(year);
        eventDTO.setMonth(month);
        eventDTO.setEventType(EventType.RESULT);
        String message = "20" + String.valueOf(year) + "년 " + String.valueOf(month) + "월 이 달의 독후감 선정 결과입니다.";
        eventDTO.setEventText(message);
        noticeService.setEvent(eventDTO.toVO());
    }
}
