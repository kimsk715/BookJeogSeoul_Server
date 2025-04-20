package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.Period;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
public class AdminDiscussionDTO {
    private List<DiscussionDTO> discussionDTOList;
    private Pagination pagination;

}
