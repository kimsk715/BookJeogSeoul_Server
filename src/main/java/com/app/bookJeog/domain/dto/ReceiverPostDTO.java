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
public class ReceiverPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String receiverTitle;
    private String receiverText;

    // 기부글 썸네일 이미지
    private String receiverFileName; 
    private String receiverFilePath;

    // 단체 프로필 이미지
    private String profileFileName;
    private String profileFilePath;

    private String createdDate;
    private String sponsorName;
    private int likeScore;
    private int commentCount;
    private String updatedDate;
}
