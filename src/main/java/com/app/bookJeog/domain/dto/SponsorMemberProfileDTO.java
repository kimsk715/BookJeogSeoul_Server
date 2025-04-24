package com.app.bookJeog.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SponsorMemberProfileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String sponsorName;
    private String fileName;
    private String filePath;
    private String createdDate;
    private String sponsorMainAddress;
    private String sponsorSubAddress;
    private String sponsorPhoneNumber;
}
