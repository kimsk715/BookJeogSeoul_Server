package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import com.app.bookJeog.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
        private final MemberDAO memberDAO;


    @Override
    public List<PersonalMemberVO> getAllPersonal(Pagination pagination) {
        return memberDAO.findAllPersonal(pagination);
    }

    @Override
    public int countAllPersonal(Pagination pagination) {
        return memberDAO.countAllPersonal(pagination);
    }
}
