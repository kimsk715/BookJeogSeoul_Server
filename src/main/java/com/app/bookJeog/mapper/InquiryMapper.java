package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.MemberInquiryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    public List<MemberInquiryVO> selectAllMemberInquiry(Pagination pagination);

    public int countAllMemberInquiry();
}
