package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;

import java.util.List;

public interface MemberService {
        public List<PersonalMemberVO> getAllPersonal(Pagination pagination);

        public int countAllPersonal(Pagination pagination);
}
