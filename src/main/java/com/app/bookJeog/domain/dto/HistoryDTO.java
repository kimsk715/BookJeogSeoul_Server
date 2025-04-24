package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.HistoryVO;
import com.app.bookJeog.domain.vo.Period;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistoryDTO{
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;
    private String bookSummary;
    private String createdDate;
    private String updatedDate;

    public HistoryVO toVO() {
        return HistoryVO.builder()
                .id(id)
                .memberId(memberId)
                .bookIsbn(bookIsbn)
                .bookSummary(bookSummary)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }
}
