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


    public MemberInquiryFileVO(String createdDate, String updatedDate, String fileName, String filePath, String fileText, Long id, Long id1, Long memberInquiryId) {
        super(createdDate, updatedDate, fileName, filePath, fileText, id);
        this.id = id1;
        this.memberInquiryId = memberInquiryId;
    }
}
