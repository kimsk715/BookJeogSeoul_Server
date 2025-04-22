package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportDAO {
    private final ReportMapper reportMapper;
    public List<BookPostReportVO> findAllBookPostReport(Pagination pagination){
        return reportMapper.selectAllBookPostReport(pagination);
    }

    public int countAllBooKPostReport(Pagination pagination){
        return reportMapper.countAllBooKPostReport(pagination);
    }

    public BookPostReportVO findBookPostReport(Long inquiryId) {
        return reportMapper.selectBookPostReport(inquiryId);
    }

    public void updateReportStatus(Long inquiryId) {
        reportMapper.updateReportStatus(inquiryId);
    }
}
