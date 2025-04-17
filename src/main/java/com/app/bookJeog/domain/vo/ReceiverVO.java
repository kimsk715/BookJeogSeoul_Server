package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostStatus;
import lombok.*;
import org.springframework.stereotype.Component;

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


    @Builder
    public ReceiverVO(String createdDate, String updatedDate, Long id, String receiverText, String receiverTitle) {
        super(createdDate, updatedDate);
        this.id = id;
        this.receiverText = receiverText;
        this.receiverTitle = receiverTitle;
    }
}
