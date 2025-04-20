package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    public List<PersonalMemberVO> selectAllPersonal(Pagination pagination);

    public int countAllPersonal(Pagination pagination);
}
