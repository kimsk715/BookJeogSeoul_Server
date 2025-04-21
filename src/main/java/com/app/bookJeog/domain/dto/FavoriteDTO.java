package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.BookCategory;
import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.domain.vo.FavoriteVO;
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
public class FavoriteDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private BookCategory category;

    public FavoriteVO toVO() {
        return FavoriteVO.builder()
                .id(id)
                .memberId(memberId)
                .category(category)
                .build();
    }


}
