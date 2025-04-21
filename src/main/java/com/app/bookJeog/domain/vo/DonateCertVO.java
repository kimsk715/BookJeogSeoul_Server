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
@SuperBuilder
public class DonateCertVO extends PostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String donateCertTitle;
    private String donateCertText;


    public DonateCertVO(String createdDate, String updatedDate, Long id, Long memberId, PostType postType, String donateCertText, String donateCertTitle, Long id1) {
        super(createdDate, updatedDate, id, memberId, postType);
        this.donateCertText = donateCertText;
        this.donateCertTitle = donateCertTitle;
        this.id = id1;
    }
}
