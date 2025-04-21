package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.domain.vo.DonateCertVO;
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
public class DonateCertDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String donateCertTitle;
    private String donateCertText;

    public DonateCertVO toVO() {
        return DonateCertVO.builder()
                .id(id)
                .donateCertTitle(donateCertTitle)
                .donateCertText(donateCertText)
                .build();
    }


}
