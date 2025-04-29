package com.app.bookJeog.controller;

import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.enumeration.ReportType;
import com.app.bookJeog.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
