package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookPostFileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookPostId;



    @Builder
    public BookPostFileVO(Long bookPostId, Long id) {
        this.bookPostId = bookPostId;
        this.id = id;
    }
}
