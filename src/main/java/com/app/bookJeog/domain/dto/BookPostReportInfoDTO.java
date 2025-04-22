package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostReportStatus;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// 독후감 신고 화면 출력용
// BookPostReport + 기타 정보
public class BookPostReportInfoDTO {
    @EqualsAndHashCode.Include
    private BookPostReportVO bookPostReportVO;
    private String memberName; // tbl_member
    private String bookPostTitle; // tbl_book_post
    private String bookTitle; // tbl_book_post + API

}
