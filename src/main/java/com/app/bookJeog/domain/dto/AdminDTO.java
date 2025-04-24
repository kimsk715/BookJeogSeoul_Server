package com.app.bookJeog.domain.dto;


import com.app.bookJeog.domain.enumeration.AdminMemberStatus;
import com.app.bookJeog.domain.vo.AdminVO;
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
public class AdminDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String adminId;
    private String adminPassword;
    private String adminName;
    private String adminDepartment;
    private String adminGrade;
    private AdminMemberStatus adminMemberStatus;
    private String createdDate;
    private String updatedDate;

    public AdminVO toVO() {
        return AdminVO.builder()
                .id(id)
                .adminId(adminId)
                .adminPassword(adminPassword)
                .adminName(adminName)
                .adminDepartment(adminDepartment)
                .adminGrade(adminGrade)
                .adminMemberStatus(adminMemberStatus)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();
    }


}
