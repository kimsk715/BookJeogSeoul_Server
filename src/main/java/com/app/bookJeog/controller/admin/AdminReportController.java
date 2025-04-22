package com.app.bookJeog.controller.admin;


import com.app.bookJeog.domain.dto.AdminBookPostReportDTO;
import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.dto.BookPostReportInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostReportVO;
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
    @GetMapping("admin/book-post-reports")
    @ResponseBody
    public AdminBookPostReportDTO getAllBookPostReport(Pagination pagination, @RequestParam("page") int page, @RequestParam(value = "keyword", required = false) String keyword ) {
        AdminBookPostReportDTO adminBookPostReportDTO = new AdminBookPostReportDTO();

        pagination.create(reportService.countAllBooKPostReport(pagination));
        List<BookPostReportInfoDTO> bookPostReportDTOList = reportService.getAllBookPostReportInfo(pagination);

        adminBookPostReportDTO.setBookPostReportInfoDTOList(bookPostReportDTOList);
        adminBookPostReportDTO.setPagination(pagination);
        log.info("컨트롤러 : {}", adminBookPostReportDTO);
        return adminBookPostReportDTO;
    }
}
