package com.app.bookJeog.domain.vo;

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
public class PostVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private PostType postType;

}
