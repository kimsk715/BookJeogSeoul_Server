package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.vo.BookDonateVO;
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
public class BookLikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;
    private AlarmStatus alarmStatus;

    public BookLikeVO toVO() {
        return BookLikeVO.builder()
                .id(id)
                .memberId(memberId)
                .bookIsbn(bookIsbn)
                .alarmStatus(alarmStatus)
                .build();

    }

}
