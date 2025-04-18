package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.BookInfoVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
public class BookInfoDTO {
    private List<BookInfoVO> bookInfoList;
    private Pagination pagination;
}
