package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostReportStatus;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.CommentAlarmVO;
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
public class CommentAlarmDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;

    public CommentAlarmVO toVO() {
        return CommentAlarmVO.builder()
                .id(id)
                .commentId(commentId)
                .build();

    }

}
