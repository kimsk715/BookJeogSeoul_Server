package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import com.app.bookJeog.domain.vo.MemberHistoryVO;
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
public class MemberPersonalMemberDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private MemberType memberType;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberNickName;
    private String memberBirth;
    private int memberMileage;
    private String memberGender;
    private PersonalMemberStatus memberStatus;
}
