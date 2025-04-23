package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
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
            pagination.create(postService.countAllBookPost());
            adminBookPostDTO.setPagination(pagination);
            List<BookPostVO> tempBookPostList = postService.getAllBookPost(pagination);
//            log.info(tempBookPostList.toString());
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
                bookPostDTO.setBookPostLikeCount(bookPost.getBookPostLikeCount());
                bookPostDTO.setBookPostVoteCount(bookPost.getBookPostVoteCount());
                bookPostDTOList.add(bookPostDTO);
            }

            log.info(String.valueOf(postService.countAllBookPost()));

            adminBookPostDTO.setBookPostDTOList(bookPostDTOList);

            log.info(adminBookPostDTO.getPagination().toString());
            log.info(pagination.toString());
            return adminBookPostDTO;
        }

        @GetMapping("admin/top-posts")
        @ResponseBody
    public AdminMonthlyBookPostDTO getTopBookPosts(Pagination pagination) {
            AdminMonthlyBookPostDTO adminBookPostDTO = new AdminMonthlyBookPostDTO();
            pagination.create(postService.countTopPosts()); // top20 선정이라 고정해도 되고, count 값을 가져와도 됨.
            adminBookPostDTO.setPagination(pagination);
            List<MonthlyBookPostVO> tempList = postService.getMonthlyBookPosts(pagination);

            List<MonthlyBookPostDTO> bookPostDTOList = new ArrayList<>();
            for (MonthlyBookPostVO bookPost : tempList) {
                MonthlyBookPostDTO bookPostDTO = new MonthlyBookPostDTO();
                bookPostDTO.setId(bookPost.getId());
                bookPostDTO.setBookPostId(bookPost.getBookPostId());
                bookPostDTO.setBookPostTitle(bookPost.getBookPostTitle());
                bookPostDTO.setBookPostText(bookPost.getBookPostText());
                bookPostDTO.setBookPostLikeCount(bookPost.getBookPostLikeCount());
                bookPostDTO.setBookPostVoteCount(bookPost.getBookPostVoteCount());
                bookPostDTO.setCreatedDate(bookPost.getCreatedDate());
                bookPostDTO.setUpdatedDate(bookPost.getUpdatedDate());
                bookPostDTOList.add(bookPostDTO);
            }
//            log.info(bookPostDTOList.toString());

            adminBookPostDTO.setMonthlyBookPostDTOList(bookPostDTOList);

            log.info(adminBookPostDTO.getPagination().toString());
            return adminBookPostDTO;
        }


}
