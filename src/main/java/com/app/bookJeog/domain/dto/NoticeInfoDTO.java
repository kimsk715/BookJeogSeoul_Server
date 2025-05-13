package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.domain.vo.NoticeVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString

public class NoticeInfoDTO {
    private NoticeVO noticeVO; // 공지 텍스트를 포함한 내용
    private List<FileDTO> fileList; // 첨부 파일들

}
