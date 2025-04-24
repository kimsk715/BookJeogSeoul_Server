package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.ReceiverStatus;
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

public class ReceiverInfoDTO {
    private ReceiverDTO receiverDTO;
    private String sponsorName;
    private int receiverLikeCount;


}
