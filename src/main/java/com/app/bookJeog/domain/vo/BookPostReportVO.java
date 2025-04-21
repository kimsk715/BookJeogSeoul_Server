package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostReportStatus;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.enumeration.CommentReportStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
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


}
