package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.PostType;
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
    private Long bookId;
    private BookPostStatus bookPostStatus;

}
