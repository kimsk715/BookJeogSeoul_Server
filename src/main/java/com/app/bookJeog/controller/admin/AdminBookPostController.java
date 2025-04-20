package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminBookPostController {
    private final PostService postService ;

        @GetMapping("admin/book-posts")
        @ResponseBody
        public AdminBookPostDTO getAllBookPost(Pagination pagination) {
            AdminBookPostDTO adminBookPostDTO = new AdminBookPostDTO();
            List<BookPostVO> tempBookPostList = postService.getAllBookPost(pagination);
            log.info(tempBookPostList.toString());
            List<BookPostDTO> bookPostDTOList = new ArrayList<>();
            for (BookPostVO bookPost : tempBookPostList) {
                BookPostDTO bookPostDTO = new BookPostDTO();
                bookPostDTO.setId(bookPost.getId());
                bookPostDTO.setBookPostTitle(bookPost.getBookPostTitle());
                bookPostDTO.setBookPostText(bookPost.getBookPostText());
                bookPostDTO.setBookPostStartDate(bookPost.getBookPostStartDate());
                bookPostDTO.setBookPostEndDate(bookPost.getBookPostEndDate());
                bookPostDTO.setBookPostIsPublic(bookPost.getBookPostIsPublic());
                if(bookPost.getBookIsbn() != null) {
                    bookPostDTO.setBookIsbn(bookPost.getBookIsbn());
                }
                if(bookPost.getBookId() !=null) {
                    bookPostDTO.setBookId(bookPost.getBookId());
                }
                bookPostDTO.setCreatedDate(bookPost.getCreatedDate());
                bookPostDTO.setUpdatedDate(bookPost.getUpdatedDate());
                bookPostDTOList.add(bookPostDTO);
            }

            log.info(bookPostDTOList.toString());
            adminBookPostDTO.setBookPostDTOList(bookPostDTOList);
            log.info(adminBookPostDTO.toString());
            return adminBookPostDTO;

        }


}
