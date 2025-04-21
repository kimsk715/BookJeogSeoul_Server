package com.app.bookJeog.service;


import com.app.bookJeog.domain.dto.MemberInquiryDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;

import java.util.List;

public interface InquiryService {
    public List<MemberInquiryVO> getAllMemberInquiry(Pagination pagination);

    public int countAllMemberInquiry();

    public default MemberInquiryDTO toMemberInquiryDTO(MemberInquiryVO memberInquiryVO) {
        MemberInquiryDTO memberInquiryDTO = new MemberInquiryDTO();
        memberInquiryDTO.setId(memberInquiryVO.getId());
        memberInquiryDTO.setMemberId(memberInquiryVO.getMemberId());
        memberInquiryDTO.setMemberInquiryTitle(memberInquiryVO.getMemberInquiryTitle());
        memberInquiryDTO.setMemberInquiryText(memberInquiryVO.getMemberInquiryText());
        memberInquiryDTO.setMemberInquiryAnswer(memberInquiryVO.getMemberInquiryAnswer());
        memberInquiryDTO.setMemberInquiryType(memberInquiryVO.getMemberInquiryType());
        memberInquiryDTO.setMemberInquiryStatus(memberInquiryVO.getMemberInquiryStatus());
        memberInquiryDTO.setCreatedDate(memberInquiryVO.getCreatedDate());
        memberInquiryDTO.setUpdatedDate(memberInquiryVO.getUpdatedDate());
        return memberInquiryDTO;
    }
}
