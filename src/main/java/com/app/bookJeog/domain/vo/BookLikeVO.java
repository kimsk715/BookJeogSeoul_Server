package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookLikeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;
    private AlarmStatus alarmStatus;


    @Builder
    public BookLikeVO(String createdDate, String updatedDate, AlarmStatus alarmStatus, Long bookIsbn, Long id, Long memberId) {
        super(createdDate, updatedDate);
        this.alarmStatus = alarmStatus;
        this.bookIsbn = bookIsbn;
        this.id = id;
        this.memberId = memberId;
    }
}
