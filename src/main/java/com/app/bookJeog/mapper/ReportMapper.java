package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.CommentReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<BookPostReportVO> selectAllBookPostReport(Pagination pagination);

    int countAllBooKPostReport(Pagination pagination);

    public BookPostReportVO selectBookPostReport(Long reportId);

    public void updateReportStatus(Long reportId);

    public List<CommentReportVO> selectAllCommentReport(Pagination pagination);

    public int countAllCommentReport(Pagination pagination);

    public CommentReportVO selectCommentReport(Long reportId);

    public void updateCommentReportStatus(Long reportId);

    // 독후감 신고 작성하기(기타 선택때만 추가 설명 입력)
    public void insertBookPostReport(BookPostReportVO bookPostReportVO);

    public void insertCommentReport(CommentReportVO commentReportVO);
}
