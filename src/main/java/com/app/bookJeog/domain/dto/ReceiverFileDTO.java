package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.vo.ReceiverFileVO;
import com.app.bookJeog.domain.vo.ReceiverVO;
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
public class ReceiverFileDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long donateCertId;

    public ReceiverFileVO toVO() {
        return ReceiverFileVO.builder()
                .id(id)
                .donateCertId(donateCertId)
                .build();
    }


}
