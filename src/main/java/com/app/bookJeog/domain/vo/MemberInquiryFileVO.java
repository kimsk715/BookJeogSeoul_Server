package com.app.bookJeog.domain.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.io.File;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberInquiryFileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberInquiryId;

}
