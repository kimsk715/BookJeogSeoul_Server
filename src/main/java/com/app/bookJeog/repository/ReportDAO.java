package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.CommentReportVO;
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

    public BookPostReportVO findBookPostReport(Long reportId) {
        return reportMapper.selectBookPostReport(reportId);
    }

    public void updateReportStatus(Long reportId) {
        reportMapper.updateReportStatus(reportId);
    }

    public List<CommentReportVO> findAllCommentReport(Pagination pagination) {
        return reportMapper.selectAllCommentReport(pagination);
    }

    public int countAllCommentReport(Pagination pagination) {
        return reportMapper.countAllCommentReport(pagination);
    }

    public CommentReportVO findCommentReport(Long reportId) {
        return reportMapper.selectCommentReport(reportId);
    }

    public void updateCommentStatus(Long reportId) {
        reportMapper.updateCommentReportStatus(reportId);
    }
}
