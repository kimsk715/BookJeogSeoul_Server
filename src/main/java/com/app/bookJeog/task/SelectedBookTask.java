package com.app.bookJeog.task;

import com.app.bookJeog.domain.dto.MonthlyBookPostDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.SelectedBookDTO;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.service.PostService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class SelectedBookTask {
    private final BookService bookService;
    private final PostService postService;

    /*
     *   0 * * * * * : 매 분 0초마다
     *   0/5 * * * * * : 매 5초 간격
     *   0 0/1 * * * : 매 1분 간격
     *   0 0/5 * ? : 매 5분 간격
     *   0 0 0/1 * * * : 매 1시간 간격
     *   0 0 0 * * ? : 매일 0시 마다
     *   0 0 0 1 * ? : 매월 1일 마다
     *   * 10-13 * * * * : 매 10, 11, 12, 13분에 동작한다.
     * */
// 매 달 1일 마다 임시 선정 도서를 실제 선정 도서
    @Scheduled(cron = "0 0 0 1 * *")
    public void insertSelectedBook() {
        List<TempSelectedBookVO> tempList = bookService.getTempSelectedBook();
        log.info(tempList.toString());
        List<SelectedBookVO> selectedList = new ArrayList<>();
        for( TempSelectedBookVO temp : tempList ) {
            SelectedBookDTO selectedDTO = new SelectedBookDTO();
            selectedDTO.setId(temp.getId());
            selectedDTO.setBookIsbn(temp.getBookIsbn());
            selectedDTO.setBookImageUrl("1"); // 임시 테스트용 여기 자리에 표지 이미지 가져올 수 있는 API 연결.
            SelectedBookVO selectedVO = selectedDTO.toSelectedBookVO();
            log.info(selectedVO.toString());
            selectedList.add(selectedVO);
        }
        log.info(selectedList.toString());

        selectedList.forEach(bookService::insertSelectedBook);
    }

    @Scheduled(cron = "0 0 0 1 * ?")
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

    @Scheduled(cron = "0 0 0 1 * ?")
    public void insertTopBookPost() {
        Optional<MonthlyBookPostVO> topPost = postService.getBestPost();
        log.info(topPost.toString());
        MonthlyBookPostVO foundTopPost = topPost.orElse(null);
        log.info(foundTopPost.toString());
        postService.insertBestPost(foundTopPost);
    }
}
