package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.CommentReportStatus;
import com.app.bookJeog.domain.vo.CommentReportVO;
import com.app.bookJeog.domain.vo.CommentVO;
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
public class CommentDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String commentText;
    private Long postId;
    private Long memberId;

    public CommentVO toVO() {
        return CommentVO.builder()
                .id(id)
                .commentText(commentText)
                .postId(postId)
                .memberId(memberId)
                .build();
    }


}
