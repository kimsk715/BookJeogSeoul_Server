package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.PersonalMemberVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class PersonalMemberPostMemberProfileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberName;
    private String memberNickName;
    private Long memberId;
    private String fileName;
    private String filePath;
}
