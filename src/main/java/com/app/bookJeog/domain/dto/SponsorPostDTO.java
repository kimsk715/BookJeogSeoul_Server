package com.app.bookJeog.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorPostDTO {
    @EqualsAndHashCode.Include
    private Long id;

    private int donateCertCount;
    private int totalPostCount;
    private String sponsorName;
    private String fileName;
    private String filePath;
}
