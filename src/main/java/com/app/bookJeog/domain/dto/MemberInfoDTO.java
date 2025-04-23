package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.MemberType;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@NoArgsConstructor
// 일단 임시로 지은 이름.
// 회원 id + 회원 이름 정보를 담고 있는 DTO.
public class MemberInfoDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private MemberType memberType;
    private String memberName;
    private String sponsorName;



}
