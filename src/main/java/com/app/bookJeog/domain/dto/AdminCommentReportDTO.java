package com.app.bookJeog.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
public class AdminCommentReportDTO {
    private List<CommentReportInfoDTO> commentReportInfoDTOList;
    private Pagination pagination;
}
