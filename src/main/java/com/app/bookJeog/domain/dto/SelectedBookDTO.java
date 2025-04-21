package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.SelectedBookVO;
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

// 선정 도서 DTO
public class SelectedBookDTO {
    private Long id;
    private Long bookIsbn;
    private String bookImageUrl;

    public SelectedBookVO toSelectedBookVO(){

        return SelectedBookVO.builder().id(id).bookIsbn(bookIsbn).bookImageUrl(bookImageUrl).build();
    }

}
