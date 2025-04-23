package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.domain.vo.SponsorInquiryVO;
import com.app.bookJeog.mapper.InquiryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class InquiryDAO {
    private final InquiryMapper inquiryMapper;

    public List<MemberInquiryVO> findAllMemberInquiry(Pagination pagination) {
        return inquiryMapper.selectAllMemberInquiry(pagination);
    }
    public int countAllMemberInquiry(Pagination pagination) {
        return inquiryMapper.countAllMemberInquiry(pagination);
    }

    public Optional<MemberInquiryVO> findMemberInquiry(Long inquiryId) {
        return inquiryMapper.selectMemberInquiry(inquiryId);
    }

    public void answerPersonalInquiry(MemberInquiryVO memberInquiryVO) {
        inquiryMapper.answerPersonalInquiry(memberInquiryVO);
    }

    public List<SponsorInquiryVO> findAllSponsorInquiry(Pagination pagination) {
        return inquiryMapper.selectAllSponsorInquiry(pagination);
    }
    public int countAllSponsorInquiry(Pagination pagination) {
        return inquiryMapper.countAllSponsorInquiry(pagination);
    }

    public Optional<SponsorInquiryVO> findSponsorInquiry(Long inquiryId) {
        return inquiryMapper.selectSponsorInquiry(inquiryId);
    }

    public void answerSponsorInquiry(SponsorInquiryVO sponsorInquiryVO) {
        inquiryMapper.answerSponsorInquiry(sponsorInquiryVO);
    }
}
