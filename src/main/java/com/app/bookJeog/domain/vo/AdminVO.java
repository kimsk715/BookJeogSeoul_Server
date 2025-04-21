package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AdminMemberStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public class AdminVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long adminId;
    private String adminPassword;
    private String adminName;
    private String adminDepartMent;
    private String adminGrade;
    private AdminMemberStatus adminMemberStatus;

}
