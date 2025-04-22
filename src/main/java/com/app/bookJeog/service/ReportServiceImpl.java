package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostReportInfoDTO;
import com.app.bookJeog.domain.dto.MemberInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.enumeration.MemberType;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.MemberVO;
import com.app.bookJeog.mapper.MemberMapper;
import com.app.bookJeog.mapper.ReportMapper;
import com.app.bookJeog.repository.MemberDAO;
import com.app.bookJeog.repository.PostDAO;
import com.app.bookJeog.repository.ReportDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {
    private final ReportDAO reportDAO;
    private final MemberDAO memberDAO;
    private final PostDAO postDAO;
    @Override
    public List<BookPostReportVO> getAllBookPostReport(Pagination pagination) {
        return reportDAO.findAllBookPostReport(pagination);
    }

    @Override
    public List<BookPostReportInfoDTO> getAllBookPostReportInfo(Pagination pagination) {
        List<BookPostReportVO> bookPostReportVOList = reportDAO.findAllBookPostReport(pagination);
        List<BookPostReportInfoDTO> bookPostReportInfoDTOList = new ArrayList<>();
        for(BookPostReportVO report : bookPostReportVOList) {
            BookPostReportInfoDTO bookPostReportInfoDTO = new BookPostReportInfoDTO();
            bookPostReportInfoDTO.setBookPostReportVO(report);
            MemberType memberType = memberDAO.findById(report.getBookPostReporterId()).getMemberType();
            if(memberType.getCode().equals("개인")){
                bookPostReportInfoDTO.setMemberName(memberDAO.findPersonalMemberById(report.getBookPostReporterId()).getMemberName());
            }
            else{
                bookPostReportInfoDTO.setMemberName(memberDAO.findSponsorMemberById(report.getBookPostReporterId()).getSponsorName());
            }

            bookPostReportInfoDTO.setBookPostTitle(postDAO.findBookPostById(report.getBookPostId()).getBookPostTitle());
            bookPostReportInfoDTOList.add(bookPostReportInfoDTO);

//            MemberInfoDTO memberInfoDTO =

//            bookPostReportInfoDTO.setMemberName(memberMapper.selectById(report.getBookPostReporterId()));
        }


        return bookPostReportInfoDTOList;
    }

    @Override
    public int countAllBooKPostReport(Pagination pagination) {
        return reportDAO.countAllBooKPostReport(pagination);
    }
}
