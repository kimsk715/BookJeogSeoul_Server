package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    // 독후감 첨부파일 넣기
    public void insertFiles(FileVO fileVO);
    public void insertBookPostFiles(BookPostFileVO bookPostFileVO);

    public void insertFile(FileVO fileVO);

    public void insertDonateCertFile(DonateCertFileVO donateCertFileVO);

    public FileVO selectDonateCertFileByPostId(Long postId);

    public List<FileVO> selectDonateCertFilesByPostId(Long postId);

    public void insertMemberInquiryFile(MemberInquiryFileVO memberInquiryFileVO);

    public void insertMemberProfile(MemberProfileVO memberProfileVO);

    public void insertNoticeFile(NoticeFileVO noticeFileVO);

    public void insertReceiverFile(ReceiverFileVO receiverFileVO);

    public FileVO selectReceiverFileByPostId(Long postId);

    public List<FileVO> selectReceiverFilesByPostId(Long postId);

    public void insertSponsorInquiryFile(SponsorInquiryFileVO sponsorInquiryFileVO);
}
