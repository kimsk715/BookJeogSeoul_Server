package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.dto.CommentReportDTO;
import com.app.bookJeog.domain.dto.PersonalMemberDTO;
import com.app.bookJeog.domain.dto.SponsorMemberDTO;
import com.app.bookJeog.domain.enumeration.CommentReportType;
import com.app.bookJeog.domain.enumeration.ReportType;
import com.app.bookJeog.domain.vo.SponsorMemberVO;
import com.app.bookJeog.service.ReportService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class ReportController  {
    private final ReportService reportService;

    // 독후감 신고 작성하기(기타 선택때만 추가 설명 입력)
    @PostMapping("/post/report-add")
    @ResponseBody
    public void addReportService(@RequestBody BookPostReportDTO bookPostReportDTO) {
        log.info("받은 신고 타입: " + bookPostReportDTO.getBookPostReportType());
        reportService.insertBookPostReport(bookPostReportDTO);
    }

    @GetMapping("report-comment")
    @ResponseBody
    public void reportComment(HttpSession session, @RequestParam Long commentId, @RequestParam CommentReportType reportType) {
        CommentReportDTO commentReportDTO = new CommentReportDTO();
        commentReportDTO.setCommentId(commentId);
        Long memberId = 0L;
        if(session.getAttribute("member") != null){
            PersonalMemberDTO personalMemberDTO = (PersonalMemberDTO) session.getAttribute("member");
            memberId = personalMemberDTO.getId();
        }
        else{
            SponsorMemberVO sponsorMemberVO = (SponsorMemberVO) session.getAttribute("sponsorMember");
            memberId = sponsorMemberVO.getId();
        }
        commentReportDTO.setCommentReporterId(memberId);

        commentReportDTO.setCommentReportType(reportType);
        log.info(commentReportDTO.toString());
        reportService.insertCommentReport(commentReportDTO);
    }


}
