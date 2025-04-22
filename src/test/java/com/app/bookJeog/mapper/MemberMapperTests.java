package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.repository.MemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Test
    public void test(){
        MemberVO member = memberMapper.selectById(1003L);
        log.info(member.getMemberType().getCode());


    }

}
