package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.service.*;
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
    private final CommentService commentService;

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
//        log.info(memberId.toString());
        memberService.updateMemberStatus(memberId);
    }

    @GetMapping("admin/comment-reports")
    @ResponseBody
    public AdminCommentReportDTO getAllCommentReport(Pagination pagination, @RequestParam("page") int page, @RequestParam(value = "keyword", required = false) String keyword) {
        AdminCommentReportDTO adminCommentReportDTO = new AdminCommentReportDTO();
        pagination.setKeyword(keyword);
        pagination.create(reportService.countAllCommentReport(pagination));
        adminCommentReportDTO.setPagination(pagination);
        adminCommentReportDTO.setCommentReportInfoDTOList(reportService.getAllCommentReportInfo(pagination));

        return adminCommentReportDTO;
    }

    @GetMapping("admin/comment-report")
    @ResponseBody
    public CommentReportInfoDTO getCommentReportDetail(@RequestParam("id") Long inquiryId){
//        log.info(reportService.getCommentReportInfo(inquiryId).toString());
        return reportService.getCommentReportInfo(inquiryId);
    }

    @GetMapping("admin/answer-comment")
    @ResponseBody
    public void commentReportProcess(@RequestParam("id") Long reportId, @RequestParam("comment-id") Long commentId) {
        commentService.updateCommentStatus(commentId);
        log.info(reportId.toString());
        reportService.updateCommentReportStatus(reportId);
        Long memberId = commentService.getCommentById(commentId).getMemberId();
        log.info(memberId.toString());
        memberService.updateMemberStatus(memberId);
    }
}
