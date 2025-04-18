package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonalMemberVO extends MemberVO{
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberNickName;
    private String memberBirth;
    private int memberMileage;
    private String memberGender;
    private PersonalMemberStatus memberStatus;

    @Builder
    public PersonalMemberVO(Long id, String memberBirth, String memberEmail, String memberGender, int memberMileage, String memberName, String memberNickName, String memberPassword, String memberPhone, PersonalMemberStatus memberStatus) {
        this.id = id;
        this.memberBirth = memberBirth;
        this.memberEmail = memberEmail;
        this.memberGender = memberGender;
        this.memberMileage = memberMileage;
        this.memberName = memberName;
        this.memberNickName = memberNickName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberStatus = memberStatus;
    }
}
