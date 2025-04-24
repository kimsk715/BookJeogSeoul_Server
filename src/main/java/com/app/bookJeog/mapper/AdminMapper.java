package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.AdminVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AdminMapper {
    
    // 관리자 로그인
    public Optional<AdminVO> selectLoginAdmin (AdminVO adminVO);
}
