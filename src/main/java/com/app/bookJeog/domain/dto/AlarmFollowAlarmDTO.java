package com.app.bookJeog.domain.dto;

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
public class AlarmFollowAlarmDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberNickName;
    private Long alarmSenderId;
    private String filePath;
    private String createdDate;
}
