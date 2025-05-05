package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.vo.BookLikeVO;
import com.app.bookJeog.domain.vo.BookPostFileVO;
import com.app.bookJeog.domain.vo.FileVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookPostFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookPostId;

    private String fileName;
    private String filePath;
    private String fileText;

    private MultipartFile multipartFile; // 실제 업로드용 파일 추가

    // 파일 자체 insert용
    public FileVO toFileVO() {
        return FileVO.builder()
                .id(id)
                .fileName(fileName)
                .filePath(filePath)
                .fileText(fileText)
                .build();
    }

    // 독후감 파일 매핑용
    public BookPostFileVO toBookPostFileVO() {
        return BookPostFileVO.builder()
                .id(id)
                .bookPostId(bookPostId)
                .build();

    }

}
