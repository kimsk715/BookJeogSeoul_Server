package com.app.bookJeog.domain.dto;

import com.app.bookJeog.domain.enumeration.PersonalMemberStatus;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonalMemberDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberNickName;
    private String memberBirth;
    private int memberMileage;
    private int memberGender;
    private PersonalMemberStatus memberStatus;
    private String createdDate;
    private String updatedDate;


    public PersonalMemberVO toVO() {
        return PersonalMemberVO.builder()
                .id(id).
                memberEmail(memberEmail).
                memberPassword(memberPassword).
                memberName(memberName).
                memberPhone(memberPhone).
                memberNickName(memberNickName).
                memberBirth(memberBirth).
                memberMileage(memberMileage).
                memberGender(memberGender).
                memberStatus(memberStatus).
                createdDate(createdDate).
                updatedDate(updatedDate).
                build();
    }




}
