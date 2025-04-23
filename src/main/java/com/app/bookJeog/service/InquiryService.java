package com.app.bookJeog.service;


import com.app.bookJeog.domain.dto.MemberInquiryDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.SponsorInquiryDTO;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.domain.vo.SponsorInquiryVO;

import java.util.List;
import java.util.Optional;

public interface InquiryService {
    public List<MemberInquiryVO> getAllMemberInquiry(Pagination pagination);

    public int countAllMemberInquiry(Pagination pagination);

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

    public default SponsorInquiryDTO tosponsorInquiryDTO(SponsorInquiryVO SponsorInquiryVO) {
        SponsorInquiryDTO sponsorInquiryDTO = new SponsorInquiryDTO();
        sponsorInquiryDTO.setId(SponsorInquiryVO.getId());
        sponsorInquiryDTO.setSponsorId(SponsorInquiryVO.getSponsorId());
        sponsorInquiryDTO.setSponsorInquiryTitle(SponsorInquiryVO.getSponsorInquiryTitle());
        sponsorInquiryDTO.setSponsorInquiryText(SponsorInquiryVO.getSponsorInquiryText());
        sponsorInquiryDTO.setSponsorInquiryAnswer(SponsorInquiryVO.getSponsorInquiryAnswer());
        sponsorInquiryDTO.setSponsorInquiryType(SponsorInquiryVO.getSponsorInquiryType());
        sponsorInquiryDTO.setSponsorInquiryStatus(SponsorInquiryVO.getSponsorInquiryStatus());
        sponsorInquiryDTO.setCreatedDate(SponsorInquiryVO.getCreatedDate());
        sponsorInquiryDTO.setUpdatedDate(SponsorInquiryVO.getUpdatedDate());
        return sponsorInquiryDTO;
    }

    public Optional<MemberInquiryVO> getMemberInquiry(Long inquiryId);

    public void answerPersonalInquiry(MemberInquiryVO memberInquiryVO);

    public List<SponsorInquiryVO> getAllSponsorInquiry(Pagination pagination);

    public int countAllSponsorInquiry(Pagination pagination);

    public Optional<SponsorInquiryVO> getSponsorInquiry(Long inquiryId);

    public void answerSponsorInquiry(SponsorInquiryVO sponsorInquiryVO);
}
