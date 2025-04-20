package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

    public List<PersonalMemberVO> findAllPersonal(Pagination pagination){
        return memberMapper.selectAllPersonal(pagination);
    }

    public int countAllPersonal(Pagination pagination){
        return memberMapper.countAllPersonal(pagination);
    }
}
