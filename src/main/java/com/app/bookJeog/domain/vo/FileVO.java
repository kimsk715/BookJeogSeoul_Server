package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.SponsorInquiryStatus;
import com.app.bookJeog.domain.enumeration.SponsorInquiryType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
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

}
