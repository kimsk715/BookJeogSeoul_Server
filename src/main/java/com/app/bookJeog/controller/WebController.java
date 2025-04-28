package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.BookInfoDTO;
import com.app.bookJeog.domain.dto.NewBookDTO;
import com.app.bookJeog.domain.dto.TopBookDTO;
import com.app.bookJeog.domain.vo.BookInfoVO;
import com.app.bookJeog.domain.vo.TopBookVO;
import com.app.bookJeog.service.AladinService;
import com.app.bookJeog.service.BookService;
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
    private BookInfoDTO bookInfoDTO;

    // 메인으로 이동
    @GetMapping("main/main")
    public String goToMain(Model model) throws IOException {
            model.addAttribute("bookInfoDTO",bookService.getPopularBooks());
            List<TopBookVO> bookInfoDTOList = bookService.getPopularBooks();
            List<TopBookDTO> topBookDTOS = new ArrayList<>();
            for (TopBookVO topBookVO : bookInfoDTOList) {
                TopBookDTO topBookDTO = new TopBookDTO();
                topBookDTO.setTopBookVO(topBookVO);
                topBookDTO.setImageUrl(aladinService.getBookCover(Long.valueOf(topBookVO.getIsbn())));
                topBookDTOS.add(topBookDTO);
            }


            List<NewBookDTO> newBooks = bookService.getNewBooks();


        model.addAttribute("topBookDTOS",topBookDTOS);

        return "main/main";
    }
}
