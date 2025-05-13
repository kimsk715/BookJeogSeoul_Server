package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.enumeration.AlarmType;
import com.app.bookJeog.domain.enumeration.EventType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class EventVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private int year;
    private int month;
    private EventType eventType;
    private String eventText;
}
