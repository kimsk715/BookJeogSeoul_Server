package com.app.bookJeog.domain.vo;

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
@SuperBuilder
public class CommentReportVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;
    private String commentReportType;
    private String commentReportText;
    private CommentReportStatus commentReportStatus;


    public CommentReportVO(String createdDate, String updatedDate, Long commentId, CommentReportStatus commentReportStatus, String commentReportText, String commentReportType, Long id) {
        super(createdDate, updatedDate);
        this.commentId = commentId;
        this.commentReportStatus = commentReportStatus;
        this.commentReportText = commentReportText;
        this.commentReportType = commentReportType;
        this.id = id;
    }
}
