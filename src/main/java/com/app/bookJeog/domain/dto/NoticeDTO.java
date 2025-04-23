package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.domain.vo.NoticeVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoticeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String noticeTitle;
    private String noticeText;
    private String createdDate;
    private String updatedDate;


    public NoticeVO toVO() {
        return NoticeVO.builder()
                .id(id)
                .noticeTitle(noticeTitle)
                .noticeText(noticeText)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }


}
