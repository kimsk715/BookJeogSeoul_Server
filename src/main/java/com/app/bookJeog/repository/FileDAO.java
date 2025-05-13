package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.BookPostFileDTO;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.mapper.FileMapper;
import com.app.bookJeog.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper ;
    private final NoticeMapper noticeMapper;

    // 독후감 첨부파일 넣기
    public void insertFiles(FileVO fileVO){
        fileMapper.insertFiles(fileVO);
    };
    public void insertBookPostFiles(BookPostFileVO bookPostFileVO){fileMapper.insertBookPostFiles(bookPostFileVO);};

    public FileVO setFile(FileVO fileVO){
        fileMapper.insertFile(fileVO);
        return fileVO;
    }

    public void setDonateCertFile(DonateCertFileVO donateCertFileVO){
        fileMapper.insertDonateCertFile(donateCertFileVO);
    }

    public FileVO findDonateCertFileByPostId(Long postId){
        return fileMapper.selectDonateCertFileByPostId(postId);
    }

    public List<FileVO> findDonateCertFilesByPostId(Long postId){
        return fileMapper.selectDonateCertFilesByPostId(postId);
    }

    public void setMemberInquiryFile(MemberInquiryFileVO memberInquiryFileVO){
        fileMapper.insertMemberInquiryFile(memberInquiryFileVO);
    }

    public void setMemberProfile(MemberProfileVO memberProfileVO){
        fileMapper.insertMemberProfile(memberProfileVO);
    }

    public void setNoticeFile(NoticeFileVO noticeFileVO){
        fileMapper.insertNoticeFile(noticeFileVO);
    }

    public List<FileVO> findNoticeFilesByNoticeId(Long noticeId){
        return fileMapper.selectNoticeFilesByNoticeId(noticeId);
    }

    public void setReceiverFile(ReceiverFileVO receiverFileVO){
        fileMapper.insertReceiverFile(receiverFileVO);
    }

    public FileVO findReceiverFileByPostId(Long postId){
        return fileMapper.selectReceiverFileByPostId(postId);
    }

    public List<FileVO> findReceiverFilesByPostId(Long postId){
        return fileMapper.selectReceiverFilesByPostId(postId);
    }


    public void setSponsorInquiryFile(SponsorInquiryFileVO sponsorInquiryFileVO){
        fileMapper.insertSponsorInquiryFile(sponsorInquiryFileVO);
    }

    // 독후감 첨부파일 수정용 조회
    public List<BookPostFileDTO> findWrittenBookPostFiles(Long bookPostId){
        return fileMapper.selectWrittenBookPostFiles(bookPostId);
    };

    // 독후감 첨부파일 삭제(수정용)
    public void deleteFiles(Long id){fileMapper.deleteFiles(id);};
    public void deleteBookPostFiles(Long id){fileMapper.deleteBookPostFiles(id);};

    // 독후감 첨부파일 메모만 변경
    public void setFileText(String fileText, Long id){fileMapper.updateFileText(fileText, id);};

    // 독후감 첨부파일 이미지만 변경
    public void updateFile(FileVO fileVO){fileMapper.updateFile(fileVO);}
//   슈퍼 키 삭제
    public void deleteFile(Long fileId){
        fileMapper.deleteFile(fileId);
    }
//    서브 키 삭제
    public void deleteReceiverFileByPostId(Long postId){
        fileMapper.deleteReceiverFileByPostId(postId);
    }

    public void deleteDonateCertFileByPostId(Long postId){
        fileMapper.deleteDonateCertFileByPostId(postId);
    }
}
