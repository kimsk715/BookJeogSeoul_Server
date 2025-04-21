package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PostAlarmVO extends AlarmVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;

    @Builder
    public PostAlarmVO(String createdDate, String updatedDate, Long alarmReceiverId, AlarmStatus alarmStatus, Long id, Long commentId, Long id1) {
        super(createdDate, updatedDate, alarmReceiverId, alarmStatus, id);
        this.commentId = commentId;
        this.id = id1;
    }
}
