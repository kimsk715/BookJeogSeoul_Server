package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminDiscussionDTO;
import com.app.bookJeog.domain.dto.DiscussionDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.AdminVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminDicussionController {
    private final PostService postService;
    private final DiscussionDTO discussionDTO;

    @GetMapping("admin/discussions")
    @ResponseBody
    public AdminDiscussionDTO getAllDiscussionPost(Pagination pagination, @RequestParam("page") int page, @RequestParam(value = "keyword", required = false) String keyword) {
        String decodedKeyword = "";
        if(keyword != null) {
            decodedKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);
        }
        pagination.setKeyword(decodedKeyword);
        AdminDiscussionDTO adminDiscussionDTO = new AdminDiscussionDTO();
        pagination.create(postService.countAllDiscussionPost(pagination));
        adminDiscussionDTO.setPagination(pagination);
        List<DiscussionVO> tempDiscussionList = postService.getAllDiscussionPost(pagination);
//        log.info("{}", tempDiscussionList);
        List<DiscussionDTO> discussionDTOList = new ArrayList<>();
        for (DiscussionVO discussion : tempDiscussionList) {
            DiscussionDTO discussionDTO = new DiscussionDTO();
            discussionDTO.setId(discussion.getId());
            discussionDTO.setDiscussionTitle(discussion.getDiscussionTitle());
            discussionDTO.setDiscussionText(discussion.getDiscussionText());
            discussionDTO.setBookIsbn(discussion.getBookIsbn());
            discussionDTO.setUpdatedDate(discussion.getUpdatedDate());
            discussionDTO.setCreatedDate(discussion.getCreatedDate());
            discussionDTOList.add(discussionDTO);
        }
//        log.info("{}", discussionDTOList);
        adminDiscussionDTO.setDiscussionDTOList(discussionDTOList);

//        log.info(adminDiscussionDTO.toString());
        return adminDiscussionDTO;
    }

    @GetMapping("admin/insert-discussion")
    @ResponseBody
    public void insertDiscussion(@RequestParam String title, @RequestParam String text, @RequestParam Long isbn, @RequestParam("book-title") String bookTitle, HttpSession session) {
      Optional<AdminVO> admin = (Optional<AdminVO>) session.getAttribute("admin");
      PostVO postVO = postService.toDiscussionVO(admin.get().getId());
        log.info("Before : {} ",  postVO);
          postService.insertPost(postVO);
        log.info("After : {} ",  postVO);
        DiscussionDTO discussionDTO = new DiscussionDTO();
        discussionDTO.setId(postVO.getId());
       discussionDTO.setDiscussionTitle(title);
       discussionDTO.setDiscussionText(text);
       discussionDTO.setBookIsbn(isbn);
       discussionDTO.setBookTitle(bookTitle);
       postService.insertDiscussion(discussionDTO);
       log.info(discussionDTO.toString());
    }
}
