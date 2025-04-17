package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FollowAlarmVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long alarmSenderId;

    @Builder
    public FollowAlarmVO(String createdDate, String updatedDate, Long alarmSenderId, Long id) {
        super(createdDate, updatedDate);
        this.alarmSenderId = alarmSenderId;
        this.id = id;
    }
}
