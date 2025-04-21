package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookPostLikeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookPostId;
    private Long memberId;



    @Builder
    public BookPostLikeVO(String createdDate, String updatedDate, Long bookPostId, Long id, Long memberId) {
        super(createdDate, updatedDate);
        this.bookPostId = bookPostId;
        this.id = id;
        this.memberId = memberId;
    }
}
