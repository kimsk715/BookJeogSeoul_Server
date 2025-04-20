package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
public class PersonalMemberDTO {
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberNickname;
    private int memberMileage;
    private PersonalMemberStatus memberStatus;
    private String createdDate;
    private String updatedDate;
}
