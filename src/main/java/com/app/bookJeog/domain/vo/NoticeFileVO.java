package com.app.bookJeog.domain.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class NoticeFileVO extends FileVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long noticeId;

    public NoticeFileVO(Long id, Long noticeId) {
        this.id = id;
        this.noticeId = noticeId;
    }
}
