package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.domain.vo.SponsorInquiryVO;
import com.app.bookJeog.repository.InquiryDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class InquiryServiceImpl implements InquiryService{

    private final InquiryDAO inquiryDAO;
    @Override
    public List<MemberInquiryVO> getAllMemberInquiry(Pagination pagination) {
        return inquiryDAO.findAllMemberInquiry(pagination);
    }

    @Override
    public int countAllMemberInquiry(Pagination pagination) {
        return inquiryDAO.countAllMemberInquiry(pagination);
    }

    @Override
    public Optional<MemberInquiryVO> getMemberInquiry(Long inquiryId) {
        return inquiryDAO.findMemberInquiry(inquiryId);
    }

    @Override
    public void answerPersonalInquiry(MemberInquiryVO memberInquiryVO) {
        log.info("서비스 : {}", memberInquiryVO.toString());
        inquiryDAO.answerPersonalInquiry(memberInquiryVO);
    }

    @Override
    public List<SponsorInquiryVO> getAllSponsorInquiry(Pagination pagination) {
        return inquiryDAO.findAllSponsorInquiry(pagination);
    }

    @Override
    public int countAllSponsorInquiry(Pagination pagination) {
        return inquiryDAO.countAllSponsorInquiry(pagination);
    }

    @Override
    public Optional<SponsorInquiryVO> getSponsorInquiry(Long inquiryId) {
        return inquiryDAO.findSponsorInquiry(inquiryId);
    }

    @Override
    public void answerSponsorInquiry(SponsorInquiryVO sponsorInquiryVO) {
        inquiryDAO.answerSponsorInquiry(sponsorInquiryVO);
    }
}
