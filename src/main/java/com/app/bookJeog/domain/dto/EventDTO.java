package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.enumeration.AlarmType;
import com.app.bookJeog.domain.enumeration.EventType;
import com.app.bookJeog.domain.vo.AlarmVO;
import com.app.bookJeog.domain.vo.EventVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EventDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private int year;
    private int month;
    private EventType eventType;

    public EventVO toVO(EventDTO eventDTO) {
        return EventVO.builder().id(id).year(year).month(month).eventType(eventType).build();

    }


}
