package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.CommentMentionVO;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
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
public class MemberInquiryDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberInquiryId;

    public MemberInquiryVO toVO() {
        return MemberInquiryVO.builder()
                .id(id)
                .memberId(memberInquiryId)
                .build();
    }


}
