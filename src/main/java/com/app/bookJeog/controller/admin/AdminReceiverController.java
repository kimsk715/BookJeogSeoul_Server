package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminReceiverDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.mapper.PostMapper;
import com.app.bookJeog.service.FavoriteService;
import com.app.bookJeog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class AdminReceiverController {
    private final PostService postService;
    private final FavoriteService favoriteService;

    @GetMapping("admin/receiver-posts")
    @ResponseBody
    public AdminReceiverDTO getAllReceiverPosts(Pagination pagination) {
        AdminReceiverDTO adminReceiverDTO = new AdminReceiverDTO();
        pagination.create(postService.countAllReceiverPost(pagination));
        adminReceiverDTO.setPagination(pagination);
        adminReceiverDTO.setReceiverInfoDTOList(postService.getAllReceiverPost(pagination));
        return adminReceiverDTO;
    }

    @GetMapping("admin/top-receiver")
    @ResponseBody
    public void setTopReceiver() {
        Long receiverId = favoriteService.topReceiver();
        postService.setTopReceiver(receiverId);
    }
}
