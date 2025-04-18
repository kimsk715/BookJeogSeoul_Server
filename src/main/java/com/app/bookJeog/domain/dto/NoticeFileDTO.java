package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.domain.vo.NoticeFileVO;
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
public class NoticeFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long noticeId;

    public NoticeFileVO toVO() {
        return NoticeFileVO.builder()
                .id(id)
                .noticeId(noticeId)
                .build();
    }


}
