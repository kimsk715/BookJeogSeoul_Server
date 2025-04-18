package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.vo.BookLikeVO;
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
public class BookPostFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookPostId;

    public BookLikeVO toVO() {
        return BookLikeVO.builder()
                .id(id)
                .bookIsbn(bookPostId)
                .build();

    }

}
