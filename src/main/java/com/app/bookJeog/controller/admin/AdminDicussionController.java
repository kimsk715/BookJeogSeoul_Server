package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminDiscussionDTO;
import com.app.bookJeog.domain.dto.DiscussionDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminDicussionController {
    private final PostService postService;

    @GetMapping("admin/discussions")
    @ResponseBody
    public AdminDiscussionDTO getAllDiscussionPost(Pagination pagination, @RequestParam("page") int page) {
        AdminDiscussionDTO adminDiscussionDTO = new AdminDiscussionDTO();

        List<DiscussionVO> tempDiscussionList = postService.getAllDiscussionPost(pagination);
log.info("{}", tempDiscussionList);
        List<DiscussionDTO> discussionDTOList = new ArrayList<>();
        for (DiscussionVO discussion : tempDiscussionList) {
            DiscussionDTO discussionDTO = new DiscussionDTO();
            discussionDTO.setId(discussion.getId());
            discussionDTO.setDiscussionTitle(discussion.getDiscussionTitle());
            discussionDTO.setDiscussionText(discussion.getDiscussionText());
            discussionDTOList.add(discussionDTO);
        }
log.info("{}", discussionDTOList);
        adminDiscussionDTO.setDiscussionDTOList(discussionDTOList);
        pagination.create(postService.countAllDiscussionPost(pagination));
        adminDiscussionDTO.setPagination(pagination);
        log.info(adminDiscussionDTO.toString());
        return adminDiscussionDTO;
    }

}
