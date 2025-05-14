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
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    public String goToMain(Model model, HttpSession session) throws IOException, JSONException {
            if(session.getAttribute("member") == null && session.getAttribute("sponsorMember") == null) {
                throw new LoginFailException("로그인 실패");
            }


            model.addAttribute("bookInfoDTO",bookService.getPopularBooks());
            List<TopBookVO> bookInfoDTOList = bookService.getPopularBooks();
            List<TopBookDTO> topBookDTOS = new ArrayList<>();
            for (TopBookVO topBookVO : bookInfoDTOList) {
                TopBookDTO topBookDTO = new TopBookDTO();
                topBookDTO.setTopBookVO(topBookVO);
                topBookDTO.setImageUrl(aladinService.getBookCover(Long.valueOf(topBookVO.getIsbn())));

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

               topViewBookDTO.setImageUrl(aladinService.getBookCover(memberHistoryVO.getBookIsbn()));

               // ISBN 저장
               topViewBookDTO.setIsbn(memberHistoryVO.getBookIsbn());

               // 줄거리 저장
               JSONObject json = aladinService.getBookInfo(topViewBookDTO.getIsbn());
               topViewBookDTO.setDescription(json.getString("description"));

               // ISBN으로 책 정보를 조회해서 저자(author) 저장
               topViewBookDTO.setAuthor(bookService.getBookByIsbn(topViewBookDTO.getIsbn()).get(0).getAuthor());


               // ISBN으로 책 정보를 조회해서 제목(title) 저장
               topViewBookDTO.setTitle(bookService.getBookByIsbn(topViewBookDTO.getIsbn()).get(0).getTitle());

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
               adminSelectBookDTO.setImageUrl(aladinService.getBookCover(selectedBookVO.getBookIsbn()));
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


              JSONObject json = aladinService.getBookInfo(bookPostVO.getBookIsbn());

              topBookPostDTO.setIsbn(bookPostVO.getBookIsbn());
              topBookPostDTO.setImageUrl(aladinService.getBookCover(bookPostVO.getBookIsbn()));
              topBookPostDTO.setTitle(bookService.getBookByIsbn(bookPostVO.getBookIsbn()).get(0).getTitle());
              topBookPostDTO.setAuthor(bookService.getBookByIsbn(bookPostVO.getBookIsbn()).get(0).getAuthor());
              topBookPostDTO.setDescription(json.optString("description"));
              topBookPostDTOS.add(topBookPostDTO);
          }


      }catch (Exception e){
          log.error("책 데이터 변환 중 오류 발생: ISBN = {}, error = {}", selectedBookVO.getBookIsbn(), e.getMessage());
      }
        model.addAttribute("topBookPostDTOS", topBookPostDTOS);
        List<PersonalMemberPostMemberProfileDTO> PersonalMemberPostMemberProfileDTO = memberServiceImpl.selectTopBookPostMemberProfile();
        log.info("메인페이지에서 받는 즐겨찾기 회원정보: {}", PersonalMemberPostMemberProfileDTO);

      model.addAttribute("bookPostMember", PersonalMemberPostMemberProfileDTO);



        return "main/main";
    }
    
    // 로그아웃기능
    @PostMapping("main/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/personal/login";
    }

    // 준비중인 페이지
    @GetMapping("preparing")
    public String prepare(){
        return "error/coming-soon";
    }

    // 점자책
    @GetMapping("braille-book")
    public String brailleBook(Model model) {
        List<BookDTO> bookDTOS = new ArrayList<>();

        // 1. 점자책 리스트 가져오기
        List<BrailleBookDTO> brailleBookDTOs = bookService.getBrailleBooks();
        log.info("brailleBookDTOS = {}", brailleBookDTOs);
        // 2. 점자책 기준으로 알라딘에서 검색해서 BookDTO에 담기
        for (BrailleBookDTO brailleBook : brailleBookDTOs) {
            String title = brailleBook.getBookTitle();
            String author = brailleBook.getAuthor();

            // 알라딘 검색
            AladinBookDTO aladinBookDTO = aladinService.getBooksByTitle(title, author);
            log.info("aladinBookDTO = {}", aladinBookDTO);

            if (aladinBookDTO != null) {
                // BookDTO에 값 넣기
                BookDTO bookDTO = new BookDTO();
                bookDTO.setBookTitle(aladinBookDTO.getBookTitle());
                bookDTO.setBookAuthor(aladinBookDTO.getBookAuthor());
                bookDTO.setPublisher(aladinBookDTO.getBookPublisher());
                bookDTO.setBookImage(aladinBookDTO.getBookCover());
                bookDTO.setBookIsbn(String.valueOf(aladinBookDTO.getBookIsbn()));

                bookDTOS.add(bookDTO);
            }
        }

        // 3. 모델에 담기
        model.addAttribute("bookDTOS", bookDTOS);

        return "main/braille-book";
    }

}
