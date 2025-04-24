package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.AdminDTO;
import com.app.bookJeog.domain.vo.AdminVO;
import com.app.bookJeog.repository.AdminDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    // 관리자 로그인
    @Override
    public Optional<AdminVO> loginAdmin(AdminDTO adminDTO) {
        AdminVO adminVO = toAdminVO(adminDTO);
        return adminDAO.findLoginAdmin(adminVO);
    }
}
