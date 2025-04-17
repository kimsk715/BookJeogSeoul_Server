package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.SponsorInquiryStatus;
import com.app.bookJeog.domain.enumeration.SponsorInquiryType;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FileVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String fileName;
    private String filePath;
    private String fileText;



    @Builder
    public FileVO(String createdDate, String updatedDate, String fileName, String filePath, String fileText, Long id) {
        super(createdDate, updatedDate);
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileText = fileText;
        this.id = id;
    }
}
