package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.vo.BookPostVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor

public class AdminBookPostDTO {
    private List<BookPostDTO> bookPostDTOList;
    private Pagination pagination;
}
