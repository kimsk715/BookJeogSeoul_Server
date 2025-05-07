package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.enumeration.AlarmType;
import com.app.bookJeog.domain.vo.AlarmVO;
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
public class AlarmDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long alarmReceiverId;
    private AlarmStatus alarmStatus;
    private AlarmType alarmType;

    public AlarmVO toVO(AlarmDTO alarmDTO) {
        return AlarmVO.builder()
                .id(id)
                .alarmReceiverId(alarmReceiverId)
                .alarmStatus(alarmStatus)
                .alarmType(alarmType)
                .build();
    }


}
