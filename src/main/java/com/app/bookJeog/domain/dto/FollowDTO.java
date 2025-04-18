package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostReportStatus;
import com.app.bookJeog.domain.enumeration.BookPostReportType;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.FollowVO;
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
public class FollowDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long followReceiverId;
    private Long followSenderId;

    public FollowVO toVO() {
        return FollowVO.builder()
                .id(id)
                .followReceiverId(followReceiverId)
                .followSenderId(followSenderId)
                .build();

    }

}
