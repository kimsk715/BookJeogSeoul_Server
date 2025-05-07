package com.app.bookJeog.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor

public class AlarmListDTO {
    private List<AlarmFollowAlarmDTO> alarmFollowAlarmDTOS;
    private List<AlarmCommentAlarmDTO> alarmCommentAlarmDTOS;
    private List<AlarmMentionAlarmDTO> alarmMentionAlarmDTOS;
    private List<PostAlarmPersonalMemberDTO> postAlarmPersonalMemberDTOS;
}
