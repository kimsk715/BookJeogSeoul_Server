package com.app.bookJeog.domain.vo;

import com.app.bookJeog.domain.enumeration.AdminMemberStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AdminVO extends Period {
    @EqualsAndHashCode.Include
    private Long id;
    private Long adminId;
    private String adminPassword;
    private String adminName;
    private String adminDepartMent;
    private String adminGrade;
    private AdminMemberStatus adminMemberStatus;

    @Builder
    public AdminVO(String createdDate, String updatedDate, String adminDepartMent, String adminGrade, Long adminId, AdminMemberStatus adminMemberStatus, String adminName, String adminPassword, Long id) {
        super(createdDate, updatedDate);
        this.adminDepartMent = adminDepartMent;
        this.adminGrade = adminGrade;
        this.adminId = adminId;
        this.adminMemberStatus = adminMemberStatus;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.id = id;
    }
}
