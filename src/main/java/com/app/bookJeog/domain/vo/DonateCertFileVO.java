package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DonateCertFileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long donateCertId;



    @Builder
    public DonateCertFileVO(Long donateCertId, Long id) {
        this.donateCertId = donateCertId;
        this.id = id;
    }
}
