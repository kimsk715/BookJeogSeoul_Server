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
public class SelectedBookVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookIsbn;
    private String bookImageUrl;

}
