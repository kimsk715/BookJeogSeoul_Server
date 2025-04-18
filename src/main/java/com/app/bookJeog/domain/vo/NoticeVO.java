package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoticeVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private String noticeTitle;
    private String noticeContent;


    @Builder
    public NoticeVO(String createdDate, String updatedDate, Long id, String noticeContent, String noticeTitle) {
        super(createdDate, updatedDate);
        this.id = id;
        this.noticeContent = noticeContent;
        this.noticeTitle = noticeTitle;
    }
}
