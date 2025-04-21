package com.app.bookJeog.domain.vo;

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
public class MemberHistoryVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;


    public MemberHistoryVO(String createdDate, String updatedDate, Long bookIsbn, Long id, Long memberId) {
        super(createdDate, updatedDate);
        this.bookIsbn = bookIsbn;
        this.id = id;
        this.memberId = memberId;
    }
}
