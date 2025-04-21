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
public class FollowVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long followReceiverId;
    private Long followSenderId;

}
