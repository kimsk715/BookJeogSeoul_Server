package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import com.app.bookJeog.domain.vo.BookDonateVO;
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
public class BookDonateDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;
    private BookPostStatus postStatus;

    public BookDonateVO toVO() {
        return BookDonateVO.builder()
                .id(id)
                .memberId(memberId)
                .bookIsbn(bookIsbn)
                .postStatus(postStatus)
                .build();
    }

}
