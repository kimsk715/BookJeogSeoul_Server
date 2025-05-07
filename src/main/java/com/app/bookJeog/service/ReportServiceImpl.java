package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.*;
import com.app.bookJeog.domain.vo.BookPostReportVO;
import com.app.bookJeog.domain.vo.CommentReportVO;
import com.app.bookJeog.repository.CommentDAO;
import com.app.bookJeog.repository.MemberDAO;
import com.app.bookJeog.repository.PostDAO;
import com.app.bookJeog.repository.ReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {
    private final ReportDAO reportDAO;
    private final MemberDAO memberDAO;
    private final PostDAO postDAO;

    private final CommentDAO commentDAO;
    private final BookService bookService;


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
            bookPostReportInfoDTO.setBookTitle(postDAO.findBookPostById(report.getBookPostId()).getBookTitle());
            bookPostReportInfoDTOList.add(bookPostReportInfoDTO);
        }
        return bookPostReportInfoDTOList;
    }

    @Override
    public int countAllBooKPostReport(Pagination pagination) {
        return reportDAO.countAllBooKPostReport(pagination);
    }

    @Override
    public BookPostReportInfoDTO getBookPostReportInfo(Long reportId) {
        BookPostReportInfoDTO bookPostReportInfoDTO = new BookPostReportInfoDTO();
        BookPostReportVO bookPostReportVO = reportDAO.findBookPostReport(reportId);
        bookPostReportInfoDTO.setBookPostReportVO(bookPostReportVO);
        MemberType memberType = memberDAO.findById(bookPostReportVO.getBookPostReporterId()).getMemberType();
        if(memberType.getCode().equals("개인")){
            bookPostReportInfoDTO.setMemberName(memberDAO.findPersonalMemberById(bookPostReportVO.getBookPostReporterId()).getMemberName());
        }
        else{
            bookPostReportInfoDTO.setMemberName(memberDAO.findSponsorMemberById(bookPostReportVO.getBookPostReporterId()).getSponsorName());
        }
        bookPostReportInfoDTO.setBookPostTitle(postDAO.findBookPostById(bookPostReportVO.getBookPostId()).getBookPostTitle());

        return bookPostReportInfoDTO;
    }

    @Override
    public void updateReportStatus(Long reportId) {
        reportDAO.updateReportStatus(reportId);
    }

    @Override
    public List<CommentReportVO> getAllCommentReport(Pagination pagination) {
        return reportDAO.findAllCommentReport(pagination);
    }

    @Override
    public List<CommentReportInfoDTO> getAllCommentReportInfo(Pagination pagination) {
        List<CommentReportVO> tempList = reportDAO.findAllCommentReport(pagination);
        List<CommentReportInfoDTO> commentReportInfoDTOList = new ArrayList<>();
        for(CommentReportVO report : tempList) {
            CommentReportInfoDTO reportInfoDTO = new CommentReportInfoDTO();
            reportInfoDTO.setCommentReportVO(report);
            Long memberId = commentDAO.findCommentById(report.getCommentId()).getMemberId();
            Long postId = commentDAO.findCommentById(report.getCommentId()).getPostId();

            MemberType memberType = memberDAO.findById(memberId).getMemberType();
            String memberName = "";
            if(memberType.getCode().equals("개인")){
                memberName = memberDAO.findPersonalMemberById(memberId).getMemberName();
            }
            else{
                memberName = memberDAO.findSponsorMemberById(memberId).getSponsorName();
            }

            reportInfoDTO.setMemberName(memberName);
            // 게시글 타입 별로 제목을 받아와야 하는 곳이 다름.?
            // 아님 게시글 링크만 걸어줄까?
            PostType postType = postDAO.findPostById(postId).getPostType();
        log.info(postType.getCode());
            String postTitle = switch (postType.getCode()) {
                case ("독후감") -> postDAO.findBookPostById(postId).getBookPostTitle();
                case ("토론") -> "테스트용 토론 제목";
                case ("후원인증") -> "테스트용 후원 인증 제목";
                case ("후원대상") -> "테스트용 후원 대상 제목";
                default -> "";
            };
            reportInfoDTO.setPostTitle(postTitle);
            String commentText = commentDAO.findCommentById(report.getCommentId()).getCommentText();
            reportInfoDTO.setCommentText(commentText);
            commentReportInfoDTOList.add(reportInfoDTO);
        }

        return commentReportInfoDTOList;
    }

    @Override
    public int countAllCommentReport(Pagination pagination) {
        return reportDAO.countAllCommentReport(pagination)  ;
    }

    @Override
    public CommentReportInfoDTO getCommentReportInfo(Long reportId) {
        CommentReportVO commentReportVO = reportDAO.findCommentReport(reportId);
        CommentReportInfoDTO commentReportInfoDTO = new CommentReportInfoDTO();


        commentReportInfoDTO.setCommentReportVO(commentReportVO);
            Long memberId = commentDAO.findCommentById(commentReportVO.getCommentId()).getMemberId();
            Long postId = commentDAO.findCommentById(commentReportVO.getCommentId()).getPostId();

            MemberType memberType = memberDAO.findById(memberId).getMemberType();
            String memberName = "";
            if(memberType.getCode().equals("개인")){
                memberName = memberDAO.findPersonalMemberById(memberId).getMemberName();
            }
            else{
                memberName = memberDAO.findSponsorMemberById(memberId).getSponsorName();
            }

        commentReportInfoDTO.setMemberName(memberName);
            // 게시글 타입 별로 제목을 받아와야 하는 곳이 다름.?
            // 아님 게시글 링크만 걸어줄까?
            PostType postType = postDAO.findPostById(postId).getPostType();

            String postTitle = switch (postType.getCode()) {
                case ("독후감") -> postDAO.findBookPostById(postId).getBookPostTitle();
                case ("토론") -> "테스트용 토론 제목";
                case ("후원인증") -> "테스트용 후원 인증 제목";
                case ("후원대상") -> "테스트용 후원 대상 제목";
                default -> "";
            };
        commentReportInfoDTO.setPostTitle(postTitle);
            String commentText = commentDAO.findCommentById(commentReportVO.getCommentId()).getCommentText();
        commentReportInfoDTO.setCommentText(commentText);


        return commentReportInfoDTO;
    }

    @Override
    public void updateCommentReportStatus(Long reportId) {
        reportDAO.updateCommentStatus(reportId);
    }

    // 독후감 신고 작성하기(기타 선택때만 추가 설명 입력)
    @Override
    public void insertBookPostReport(BookPostReportDTO bookPostReportDTO){
        if (bookPostReportDTO.getBookPostReportType() != BookPostReportType.ETC) {
            bookPostReportDTO.setBookPostReportText(null);
        }

        reportDAO.setBookPostReport(bookPostReportDTO.toVO());
    }

    @Override
    public void insertCommentReport(CommentReportDTO commentReportDTO) {
        if(commentReportDTO.getCommentReportType().equals(CommentReportType.ETC)){
            commentReportDTO.setCommentReportText(null);
        }
        reportDAO.setCommentReport(commentReportDTO.toVO());
    }


}
