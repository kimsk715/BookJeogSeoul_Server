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
// 임시 선정 도서 DTO
public class TempSelectedBookDTO {
    private Long id;
    private Long bookIsbn;
    private String title;
    private String classNo;
}
