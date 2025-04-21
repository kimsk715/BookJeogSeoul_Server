package com.app.bookJeog.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoticeFileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long noticeId;

    @Builder
    public NoticeFileVO(Long id, Long noticeId) {
        this.id = id;
        this.noticeId = noticeId;
    }
}
