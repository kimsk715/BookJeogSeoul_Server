package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.AdminDTO;
import com.app.bookJeog.domain.vo.AdminVO;

import java.util.Optional;

public interface AdminService {

    // 관리자 로그인

    public Optional<AdminVO> loginAdmin (AdminDTO adminDTO);

    public default AdminVO toAdminVO (AdminDTO adminDTO){
        return AdminVO.builder()
                .adminId(adminDTO.getAdminId())
                .adminPassword(adminDTO.getAdminPassword())
                .build();

    }
}
