package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.AlarmStatus;
import com.app.bookJeog.domain.vo.AlarmVO;
import com.app.bookJeog.domain.vo.BookPostLikeVO;
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
public class BookPostLikeDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookPostId;
    private Long memberId;

    public BookPostLikeVO toVO() {
        return BookPostLikeVO.builder()
                .id(id)
                .bookPostId(bookPostId)
                .memberId(memberId)
                .build();
    }


}
