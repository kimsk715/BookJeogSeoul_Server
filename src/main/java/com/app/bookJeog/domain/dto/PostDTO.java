package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.PostType;
import com.app.bookJeog.domain.vo.NoticeVO;
import com.app.bookJeog.domain.vo.PostVO;
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
public class PostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private PostType postType;

    public PostVO toVO() {
        return PostVO.builder()
                .id(id)
                .memberId(memberId)
                .postType(postType)
                .build();
    }


}
