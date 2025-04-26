package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.CommentReportVO;
import com.app.bookJeog.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
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

    // 독후감 신고 작성하기(기타 선택때만 추가 설명 입력)
    public void setBookPostReport(BookPostReportVO bookPostReportVO) {
        log.info("bookPostReportVO: {}", bookPostReportVO);
        reportMapper.insertBookPostReport(bookPostReportVO);
    };
}
