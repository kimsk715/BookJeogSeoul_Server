package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostReportStatus;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.enumeration.CommentReportStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookPostReportVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookPostId;
    private Long bookPostReporterId;
    private String bookPostReportText;
    private BookPostReportType bookPostReportType;
    private BookPostReportStatus bookPostReportStatus;



    @Builder
    public BookPostReportVO(String createdDate, String updatedDate, Long bookPostId, Long bookPostReporterId, BookPostReportStatus bookPostReportStatus, String bookPostReportText, BookPostReportType bookPostReportType, Long id) {
        super(createdDate, updatedDate);
        this.bookPostId = bookPostId;
        this.bookPostReporterId = bookPostReporterId;
        this.bookPostReportStatus = bookPostReportStatus;
        this.bookPostReportText = bookPostReportText;
        this.bookPostReportType = bookPostReportType;
        this.id = id;
    }
}
