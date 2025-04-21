package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.domain.vo.NoticeVO;
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
public class NoticeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String noticeTitle;
    private String noticeContent;

    public NoticeVO toVO() {
        return NoticeVO.builder()
                .id(id)
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .build();
    }


}
