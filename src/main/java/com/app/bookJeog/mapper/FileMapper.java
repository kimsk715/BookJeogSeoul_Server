package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.BookPostFileDTO;
import com.app.bookJeog.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    // 독후감 첨부파일 넣기
    public void insertFiles(FileVO fileVO);
    public void insertBookPostFiles(BookPostFileVO bookPostFileVO);

    // 독후감 첨부파일 수정시에 조회
    public List<BookPostFileDTO> selectWrittenBookPostFiles(Long bookPostId);

    // 독후감 첨부파일 삭제(수정용)
    public void deleteFiles(Long id);
    public void deleteBookPostFiles(Long id);

    public void insertFile(FileVO fileVO);

    public void insertDonateCertFile(DonateCertFileVO donateCertFileVO);

    public FileVO selectDonateCertFileByPostId(Long postId);

    public void insertMemberInquiryFile(MemberInquiryFileVO memberInquiryFileVO);

    public void insertMemberProfile(MemberProfileVO memberProfileVO);

    public void insertNoticeFile(NoticeFileVO noticeFileVO);

    public void insertReceiverFile(ReceiverFileVO receiverFileVO);

    public void insertSponsorInquiryFile(SponsorInquiryFileVO sponsorInquiryFileVO);
}
