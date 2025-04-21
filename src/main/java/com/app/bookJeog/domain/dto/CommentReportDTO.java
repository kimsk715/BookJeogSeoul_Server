package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.CommentReportStatus;
import com.app.bookJeog.domain.vo.CommentMentionVO;
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
public class CommentReportDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;
    private String commentReportType;
    private String commentReportText;
    private CommentReportStatus commentReportStatus;

    public CommentReportVO toVO() {
        return CommentReportVO.builder()
                .id(id)
                .commentId(commentId)
                .commentReportType(commentReportType)
                .commentReportText(commentReportText)
                .commentReportStatus(commentReportStatus)
                .build();
    }


}
