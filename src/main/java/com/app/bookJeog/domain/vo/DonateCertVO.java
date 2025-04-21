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
public class DonateCertVO extends PostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String donateCertTitle;
    private String donateCertText;

}
