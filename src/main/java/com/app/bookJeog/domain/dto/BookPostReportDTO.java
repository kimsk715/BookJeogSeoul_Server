package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.enumeration.BookPostReportStatus;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.vo.BookLikeVO;
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
public class BookPostReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookPostId;
    private Long bookPostReporterId;
    private String bookPostReportText;
    private BookPostReportType bookPostReportType;
    private BookPostReportStatus bookPostReportStatus;
    private String createdDate;
    private String updatedDate;

    public BookPostReportVO toVO() {
        return BookPostReportVO.builder()
                .id(id)
                .bookPostId(bookPostId)
                .bookPostReporterId(bookPostReporterId)
                .bookPostReportText(bookPostReportText)
                .bookPostReportType(bookPostReportType)
                .bookPostReportStatus(bookPostReportStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();

    }

}
