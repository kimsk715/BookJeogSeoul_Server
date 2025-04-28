package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiscussionVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String discussionTitle;
    private String discussionText;
    private Long bookIsbn;
    private String bookTitle;


}
