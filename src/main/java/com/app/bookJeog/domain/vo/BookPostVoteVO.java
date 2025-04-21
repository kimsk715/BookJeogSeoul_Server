package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class BookPostVoteVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookPostId;


    public BookPostVoteVO(String createdDate, String updatedDate, Long bookPostId, Long id, Long memberId) {
        super(createdDate, updatedDate);
        this.bookPostId = bookPostId;
        this.id = id;
        this.memberId = memberId;
    }
}
