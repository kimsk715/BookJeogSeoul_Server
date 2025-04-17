package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookCategory;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FavoriteVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private BookCategory category;

    @Builder
    public FavoriteVO(String createdDate, String updatedDate, BookCategory category, Long id, Long memberId) {
        super(createdDate, updatedDate);
        this.category = category;
        this.id = id;
        this.memberId = memberId;
    }
}
