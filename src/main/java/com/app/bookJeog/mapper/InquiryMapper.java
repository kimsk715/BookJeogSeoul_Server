package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.domain.vo.SponsorInquiryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InquiryMapper {
    public List<MemberInquiryVO> selectAllMemberInquiry(Pagination pagination);

    public int countAllMemberInquiry(Pagination pagination);

    public Optional<MemberInquiryVO> selectMemberInquiry(Long inquiryId);

    public void answerPersonalInquiry(MemberInquiryVO memberInquiryVO);

    public List<SponsorInquiryVO> selectAllSponsorInquiry(Pagination pagination);

    public int countAllSponsorInquiry(Pagination pagination);

    public Optional<SponsorInquiryVO> selectSponsorInquiry(Long inquiryId);

    public void answerSponsorInquiry(SponsorInquiryVO sponsorInquiryVO);
}
