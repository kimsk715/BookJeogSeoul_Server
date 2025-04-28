package com.app.bookJeog.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

// 화면용 DTO
@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DonateCertPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String donateCertTitle;
    private String donateCertText;

    // 기부글 썸네일 이미지
    private String donateCertFileName;
    private String donateCertFilePath;

    // 단체 프로필 이미지
    private String profileFileName;
    private String profileFilePath;

    private String createdDate;
    private String sponsorName;
    private String updatedDate;
    private Long memberId;

    private int commentCount;
}
