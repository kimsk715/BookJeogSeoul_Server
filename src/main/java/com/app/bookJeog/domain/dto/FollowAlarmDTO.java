package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.CommentReportStatus;
import com.app.bookJeog.domain.vo.CommentReportVO;
import com.app.bookJeog.domain.vo.FollowAlarmVO;
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
public class FollowAlarmDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long alarmSenderId;

    public FollowAlarmVO toVO() {
        return FollowAlarmVO.builder()
                .id(id)
                .alarmReceiverId(alarmSenderId)
                .build();
    }


}
