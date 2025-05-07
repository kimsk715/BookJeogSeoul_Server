package com.app.bookJeog.controller;

import com.app.bookJeog.controller.exception.LoginFailException;
import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.service.AladinService;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.service.BookServiceImpl;
import com.app.bookJeog.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
public class WebController {

    private final BookService bookService;
    private final AladinService aladinService;
    private final BookServiceImpl bookServiceImpl;
    private final SelectedBookVO selectedBookVO;
    private final MemberServiceImpl memberServiceImpl;
    private final HttpSession session;
    private BookInfoDTO bookInfoDTO;


    // 메인으로 이동
    @GetMapping("main/main")
    public String goToMain(Model model) throws IOException {
            if(session.getAttribute("member") != null && session.getAttribute("sponsor") != null) {
                throw new LoginFailException("로그인 정보가없음");
            }

            model.addAttribute("bookInfoDTO",bookService.getPopularBooks());
            List<TopBookVO> bookInfoDTOList = bookService.getPopularBooks();
            List<TopBookDTO> topBookDTOS = new ArrayList<>();
            for (TopBookVO topBookVO : bookInfoDTOList) {
                TopBookDTO topBookDTO = new TopBookDTO();
                topBookDTO.setTopBookVO(topBookVO);
//                topBookDTO.setImageUrl(aladinService.getBookCover(Long.valueOf(topBookVO.getIsbn())));
                topBookDTOS.add(topBookDTO);
            }
        model.addAttribute("topBookDTOS",topBookDTOS);


        // 가장 많이 조회된 책 리스트를 가져옴
        List<MemberHistoryVO> tempList = bookServiceImpl.selectTopViewBooks();
        log.info(tempList.toString());
        // 최종 결과를 담을 TopViewBookDTO 리스트 생성
        List<TopViewBookDTO> topViewBookDTOS = new ArrayList<>();

       try {
           // 조회된 책 리스트를 하나씩 순회
           for (MemberHistoryVO memberHistoryVO : tempList) {
               // 새로운 DTO 객체 생성
               TopViewBookDTO topViewBookDTO = new TopViewBookDTO();

               // 알라딘 API를 통해 책 표지 이미지 URL 가져오기

//               topViewBookDTO.setImageUrl(aladinService.getBookCover(memberHistoryVO.getBookIsbn()));

               // ISBN 저장
               topViewBookDTO.setIsbn(memberHistoryVO.getBookIsbn());

               // ISBN으로 책 정보를 조회해서 저자(author) 저장
//               topViewBookDTO.setAuthor(bookService.getBookByIsbn(topViewBookDTO.getIsbn()).get(0).getAuthor());


               // ISBN으로 책 정보를 조회해서 제목(title) 저장
//               topViewBookDTO.setTitle(bookService.getBookByIsbn(topViewBookDTO.getIsbn()).get(0).getTitle());

               // 완성된 DTO를 결과 리스트에 추가
               topViewBookDTOS.add(topViewBookDTO);
           }
           log.info(topViewBookDTOS.toString());
       }catch (Exception e){
           log.error("책 데이터 변환 중 오류 발생: ISBN, error = {}", e.getMessage());
       }
        model.addAttribute("topViewBooks", topViewBookDTOS);
        // 어드민이 선택한 책들 조회
        List<SelectedBookVO> templist = bookServiceImpl.selectAdminSuggestBooks();

        //결과를 담을 객체
        List<AdminSelectBookDTO> AdminSelectBookDTOS = new ArrayList<>();
       try {
           for (SelectedBookVO selectedBookVO : templist) {
               AdminSelectBookDTO adminSelectBookDTO = new AdminSelectBookDTO();
//               adminSelectBookDTO.setImageUrl(aladinService.getBookCover(selectedBookVO.getBookIsbn()));
               adminSelectBookDTO.setIsbn(selectedBookVO.getBookIsbn());
               adminSelectBookDTO.setTitle(bookService.getBookByIsbn(selectedBookVO.getBookIsbn()).get(0).getTitle());
               adminSelectBookDTO.setAuthor(bookService.getBookByIsbn(selectedBookVO.getBookIsbn()).get(0).getAuthor());
               AdminSelectBookDTOS.add(adminSelectBookDTO);
           }


       }catch (Exception e){
           log.error("책 데이터 변환 중 오류 발생: ISBN = {}, error = {}", selectedBookVO.getBookIsbn(), e.getMessage());
       }
        model.addAttribute("suggestBooks", AdminSelectBookDTOS);

       List<BookPostVO> tempPost = bookServiceImpl.selectTopBookPost();

       List<TopBookPostDTO> topBookPostDTOS = new ArrayList<>();
      try {
          for (BookPostVO bookPostVO : tempPost) {
              TopBookPostDTO topBookPostDTO = new TopBookPostDTO();
              topBookPostDTO.setIsbn(bookPostVO.getBookIsbn());
//              topBookPostDTO.setImageUrl(aladinService.getBookCover(bookPostVO.getBookIsbn()));
              topBookPostDTO.setTitle(bookService.getBookByIsbn(bookPostVO.getBookIsbn()).get(0).getTitle());
              topBookPostDTO.setAuthor(bookService.getBookByIsbn(bookPostVO.getBookIsbn()).get(0).getAuthor());
              topBookPostDTOS.add(topBookPostDTO);
          }


      }catch (Exception e){
          log.error("책 데이터 변환 중 오류 발생: ISBN = {}, error = {}", selectedBookVO.getBookIsbn(), e.getMessage());
      }
        model.addAttribute("topBookPostDTOS", topBookPostDTOS);


      model.addAttribute("bookPostMember", memberServiceImpl.selectTopBookPostMemberProfile());



        return "main/main";
    }
}
