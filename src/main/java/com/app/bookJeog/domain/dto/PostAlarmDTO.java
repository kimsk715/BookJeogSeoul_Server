package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.vo.FollowVO;
import com.app.bookJeog.domain.vo.PostAlarmVO;
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
public class PostAlarmDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long commentId;

    public PostAlarmVO toVO() {
        return PostAlarmVO.builder()
                .id(id)
                .commentId(commentId)
                .build();

    }

}
