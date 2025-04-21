package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.CommentVO;
import com.app.bookJeog.domain.vo.DonateCertFileVO;
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
public class DonateCertFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long donateCertId;

    public DonateCertFileVO toVO() {
        return DonateCertFileVO.builder()
                .id(id)
                .donateCertId(donateCertId)
                .build();
    }


}
