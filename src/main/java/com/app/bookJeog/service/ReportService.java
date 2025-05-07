package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.dto.BookPostReportInfoDTO;
import com.app.bookJeog.domain.dto.CommentReportInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.CommentReportVO;
import com.app.bookJeog.domain.vo.PostVO;

import java.util.List;
import java.util.Optional;

public interface ReportService {


    public List<BookPostReportInfoDTO> getAllBookPostReportInfo(Pagination pagination);



    int countAllBooKPostReport(Pagination pagination);

    public default BookPostReportDTO toBookPostReportDTO(BookPostReportVO bookPostReportVO){
        BookPostReportDTO bookPostReportDTO = new BookPostReportDTO();
        bookPostReportDTO.setId(bookPostReportVO.getId());
        bookPostReportDTO.setBookPostId(bookPostReportVO.getBookPostId());
        bookPostReportDTO.setBookPostReporterId(bookPostReportVO.getBookPostReporterId());
        bookPostReportDTO.setBookPostReportStatus(bookPostReportVO.getBookPostReportStatus());
        bookPostReportDTO.setBookPostReportText(bookPostReportVO.getBookPostReportText());
        bookPostReportDTO.setBookPostReportType(bookPostReportVO.getBookPostReportType());
        bookPostReportDTO.setCreatedDate(bookPostReportVO.getCreatedDate());
        bookPostReportDTO.setUpdatedDate(bookPostReportVO.getUpdatedDate());
        return bookPostReportDTO;
    };

    public BookPostReportInfoDTO getBookPostReportInfo(Long reportId);

    public void updateReportStatus(Long reportId);

    public List<CommentReportVO> getAllCommentReport(Pagination pagination);

    public List<CommentReportInfoDTO> getAllCommentReportInfo(Pagination pagination);

    public int countAllCommentReport(Pagination pagination);

    public CommentReportInfoDTO getCommentReportInfo(Long reportId);

    public void updateCommentReportStatus(Long reportId);

    // 독후감 신고 작성하기(기타 선택때만 추가 설명 입력)
    public void insertBookPostReport(BookPostReportDTO bookPostReportDTO);
}
