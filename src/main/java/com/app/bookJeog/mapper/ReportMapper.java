package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<BookPostReportVO> selectAllBookPostReport(Pagination pagination);


    int countAllBooKPostReport(Pagination pagination);
}
