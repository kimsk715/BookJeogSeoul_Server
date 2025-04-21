package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.DonateCertVO;
import com.app.bookJeog.domain.vo.FileVO;
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
public class FileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String fileName;
    private String filePath;
    private String fileText;

    public FileVO toVO() {
        return FileVO.builder()
                .id(id)
                .fileName(fileName)
                .filePath(filePath)
                .fileText(fileText)
                .build();
    }


}
