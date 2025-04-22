package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminBookPostReportDTO;
import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.dto.BookPostReportInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.service.BookService;
import com.app.bookJeog.service.MemberService;
import com.app.bookJeog.service.PostService;
import com.app.bookJeog.service.ReportService;
import lombok.Getter;
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
public class AdminReportController {
    private final ReportService reportService;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("admin/book-post-reports")
    @ResponseBody
    public AdminBookPostReportDTO getAllBookPostReport(Pagination pagination, @RequestParam("page") int page, @RequestParam(value = "keyword", required = false) String keyword ) {
        AdminBookPostReportDTO adminBookPostReportDTO = new AdminBookPostReportDTO();
        pagination.create(reportService.countAllBooKPostReport(pagination));
        List<BookPostReportInfoDTO> bookPostReportDTOList = reportService.getAllBookPostReportInfo(pagination);
        adminBookPostReportDTO.setBookPostReportInfoDTOList(bookPostReportDTOList);
        adminBookPostReportDTO.setPagination(pagination);
//        log.info("컨트롤러 : {}", adminBookPostReportDTO);
//        log.info(adminBookPostReportDTO.getBookPostReportInfoDTOList().toString());
        return adminBookPostReportDTO;
    }

    @GetMapping("admin/book-post-report")
    @ResponseBody
    public BookPostReportInfoDTO getBookPostReportDetail(@RequestParam("id") Long inquiryId){
//        log.info(reportService.getBookPostReportInfo(inquiryId).toString());
        return reportService.getBookPostReportInfo(inquiryId);
    }

    @GetMapping("admin/answer-book-post")
    @ResponseBody
    public void bookPostReportProcess(@RequestParam("id") Long reportId, @RequestParam("post-id") Long postId) {
        postService.updateBookPostStatus(postId);
        reportService.updateReportStatus(reportId);
        Long memberId = postService.getBookPostById(postId).getMemberId();
        log.info(memberId.toString());
        memberService.updateMemberStatus(memberId);
    }
}
