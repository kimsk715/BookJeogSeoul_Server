package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.Period;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiscussionDTO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String discussionTitle;
    private String discussionText;
    private Long bookIsbn;


}
