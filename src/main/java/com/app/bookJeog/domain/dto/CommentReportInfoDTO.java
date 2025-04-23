package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.CommentReportVO;
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
public class CommentReportInfoDTO {
    @EqualsAndHashCode.Include
    private CommentReportVO commentReportVO;
    private String commentText;
    private String memberName; // tbl_member
    private String postTitle; // tbl_book_post

}
