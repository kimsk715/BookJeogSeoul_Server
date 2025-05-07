package com.app.bookJeog.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor

public class FollowMemberAlarmDTO {
    private String memberNickName;
    private Long alarmSenderId;
    private String followPath;
}
