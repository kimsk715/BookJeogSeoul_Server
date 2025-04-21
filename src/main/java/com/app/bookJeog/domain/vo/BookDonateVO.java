package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AdminMemberStatus;
import com.app.bookJeog.domain.enumeration.BookPostStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class BookDonateVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private Long bookIsbn;
    private BookPostStatus postStatus;


    public BookDonateVO(String createdDate, String updatedDate, Long bookIsbn, Long id, Long memberId, BookPostStatus postStatus) {
        super(createdDate, updatedDate);
        this.bookIsbn = bookIsbn;
        this.id = id;
        this.memberId = memberId;
        this.postStatus = postStatus;
    }
}
