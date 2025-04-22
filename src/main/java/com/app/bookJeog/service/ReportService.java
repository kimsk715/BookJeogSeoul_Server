package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostReportDTO;
import com.app.bookJeog.domain.dto.BookPostReportInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostReportVO;

import java.util.List;

public interface ReportService {
    public List<BookPostReportVO> getAllBookPostReport(Pagination pagination);

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

    public BookPostReportInfoDTO getBookPostReportInfo(Long inquiryId);

    public void updateReportStatus(Long inquiryId);
}
