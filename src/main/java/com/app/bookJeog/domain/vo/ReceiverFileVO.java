package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiverFileVO  {
    @EqualsAndHashCode.Include
    private Long id;
    private Long donateCertId;



    @Builder
    public ReceiverFileVO(Long donateCertId, Long id) {
        this.donateCertId = donateCertId;
        this.id = id;
    }
}
