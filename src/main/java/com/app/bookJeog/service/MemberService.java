package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.MemberPersonalMemberDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.domain.vo.PersonalMemberVO;
import java.util.List;

public interface MemberService {
        public List<PersonalMemberVO> getAllPersonal(Pagination pagination);

        public int countAllPersonal(Pagination pagination);

    // 회원가입
    public default MemberVO toMemberVO  () {
        return MemberVO.builder().memberType(MemberType.PERSONAL).build();
    }
    public default PersonalMemberVO toPersonalMemberVO(MemberPersonalMemberDTO memberPersonalMemberDTO) {
        return PersonalMemberVO.builder()
                .id(memberPersonalMemberDTO.getId())
                .memberBirth(memberPersonalMemberDTO.getMemberBirth())
                .memberPassword(memberPersonalMemberDTO.getMemberPassword())
                .memberName(memberPersonalMemberDTO.getMemberName())
                .memberGender(memberPersonalMemberDTO.getMemberGender())
                .memberPhone(memberPersonalMemberDTO.getMemberPhone())
                .memberEmail(memberPersonalMemberDTO.getMemberEmail())
                .memberNickName(memberPersonalMemberDTO.getMemberNickName())
                .build();
    }

}
