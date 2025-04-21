package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import com.app.bookJeog.mapper.InquiryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
@RequiredArgsConstructor
public class InquiryDAO {
    private final InquiryMapper inquiryMapper;

    public List<MemberInquiryVO> findAllMemberInquiry(Pagination pagination) {
        log.info(pagination.toString());
        log.info(inquiryMapper.selectAllMemberInquiry(pagination).toString());
        return inquiryMapper.selectAllMemberInquiry(pagination);
    }

    public int countAllMemberInquiry() {
        return inquiryMapper.countAllMemberInquiry();
    }
}
