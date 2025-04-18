package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.BookPostStatus;
import com.app.bookJeog.domain.vo.MemberProfileVO;
import com.app.bookJeog.domain.vo.SelectedBookPostVO;
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
public class SelectedBookPostDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long bookId;
    private BookPostStatus bookPostStatus;

    public SelectedBookPostVO toVO() {
        return SelectedBookPostVO.builder()
                .id(id)
                .bookId(bookId)
                .bookPostStatus(bookPostStatus)
                .build();
    }


}
