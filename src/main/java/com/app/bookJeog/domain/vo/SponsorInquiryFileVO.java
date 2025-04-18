package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorInquiryFileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long sponsorInquiryId;


    @Builder
    public SponsorInquiryFileVO(String createdDate, String updatedDate, String fileName, String filePath, String fileText, Long id, Long id1, Long sponsorInquiryId) {
        super(createdDate, updatedDate, fileName, filePath, fileText, id);
        this.id = id1;
        this.sponsorInquiryId = sponsorInquiryId;
    }
}
}
