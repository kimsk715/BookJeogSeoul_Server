package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.enumeration.AlarmType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class AlarmVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long alarmReceiverId;
    private AlarmStatus alarmStatus;
    private AlarmType alarmType;
}
