package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.BookPostLikeVO;
import com.app.bookJeog.domain.vo.BookPostVoteVO;
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
public class BookPostVoteDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookPostId;

    public BookPostVoteVO toVO() {
        return BookPostVoteVO.builder()
                .id(id)
                .memberId(memberId)
                .bookPostId(bookPostId)
                .build();
    }
}
