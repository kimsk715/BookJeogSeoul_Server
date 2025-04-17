package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DonateCertVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String donateCertTitle;
    private String donateCertText;


    @Builder
    public DonateCertVO(String createdDate, String updatedDate, Long id, String donateCertTitle, String donateCertText) {
        super(createdDate, updatedDate);
        this.id = id;
        this.donateCertTitle = donateCertTitle;
        this.donateCertText = donateCertText;
    }
}
