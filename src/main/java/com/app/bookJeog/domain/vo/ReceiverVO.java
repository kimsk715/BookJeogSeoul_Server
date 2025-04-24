package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.ReceiverStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiverVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String receiverTitle;
    private String receiverText;
    private ReceiverStatus receiverStatus;

}
