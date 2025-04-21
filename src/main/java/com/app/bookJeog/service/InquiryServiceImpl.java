package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.repository.InquiryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class InquiryServiceImpl implements InquiryService{

    private final InquiryDAO inquiryDAO;
    @Override
    public List<MemberInquiryVO> getAllMemberInquiry(Pagination pagination) {
        return inquiryDAO.findAllMemberInquiry(pagination);
    }

    @Override
    public int countAllMemberInquiry() {
        return inquiryDAO.countAllMemberInquiry();
    }
}
