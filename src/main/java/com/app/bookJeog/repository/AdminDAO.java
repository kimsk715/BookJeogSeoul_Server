package com.app.bookJeog.repository;

import com.app.bookJeog.domain.vo.AdminVO;
import com.app.bookJeog.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminDAO {

    private final AdminMapper adminMapper;

    // 관리자 로그인
    public Optional<AdminVO> findLoginAdmin (AdminVO adminVO){
        return adminMapper.selectLoginAdmin(adminVO);
    }

}
