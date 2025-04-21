package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.BookPostIsPublic;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@SuperBuilder
@Component
@ToString(callSuper = true)
@Getter
@NoArgsConstructor

public class BestBookPostVO  {
   private Long id;
   private Long bookPostId;
   private String bookPostTitle;
   private String bookPostText;
   private String createdDate;
   private String updatedDate;

    @Builder
    public BestBookPostVO(Long id, Long bookPostId, String bookPostTitle, String bookPostText, String createdDate, String updatedDate) {
        this.id = id;
        this.bookPostId = bookPostId;
        this.bookPostTitle = bookPostTitle;
        this.bookPostText = bookPostText;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
