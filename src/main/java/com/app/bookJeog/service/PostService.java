package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.PostType;
import com.app.bookJeog.domain.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostService {

    //  전체 독후감 목록
    public List<BookPostVO> getAllBookPost(Pagination pagination);

    //  화면에 나올 정보를 모아놓은 DTO 로 변환하는 메소드
    public default BookPostDTO toBookPostDTO(BookPostVO bookPostVO) {
        BookPostDTO bookPostDTO = new BookPostDTO();
        if(bookPostVO != null) {
            bookPostDTO.setId(bookPostVO.getId());
            bookPostDTO.setBookPostTitle(bookPostVO.getBookPostTitle());
            bookPostDTO.setBookPostText(bookPostVO.getBookPostText());
            bookPostDTO.setBookPostIsPublic(bookPostVO.getBookPostIsPublic());
            bookPostDTO.setBookPostStartDate(bookPostVO.getBookPostStartDate());
            bookPostDTO.setBookPostEndDate(bookPostVO.getBookPostEndDate());
            if(bookPostVO.getBookId() != null) {
                bookPostDTO.setBookId(bookPostVO.getBookId());
            }
            if(bookPostVO.getBookIsbn() != null) {
                bookPostDTO.setBookIsbn(bookPostVO.getBookIsbn());
            }
            bookPostDTO.setCreatedDate(bookPostVO.getCreatedDate());
            bookPostDTO.setUpdatedDate(bookPostVO.getUpdatedDate());
        }

        return bookPostDTO;
    }

    public List<DiscussionVO> getAllDiscussionPost(Pagination pagination);

    public default DiscussionDTO toDiscussionDTO(DiscussionVO discussionVO) {
        DiscussionDTO discussionDTO = new DiscussionDTO();
        if(discussionVO != null) {
            discussionDTO.setId(discussionVO.getId());
            discussionDTO.setDiscussionTitle(discussionVO.getDiscussionTitle());
            discussionDTO.setDiscussionText(discussionVO.getDiscussionText());
        }
        return discussionDTO;
    }

    public int countAllDiscussionPost(Pagination pagination);

    //   이 책으로 작성한 독후감 일부 조회
    ArrayList<BookPostMemberDTO> selectThisBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 조회
    ArrayList<BookPostMemberDTO> selectThisBookAllPosts(Long isbn, int offset);

    //   이 책으로 작성한 독후감 전체 개수 조회
    int selectBookAllPostsCount(Long isbn);

    //   특정 독후감 상세정보 조회(회원 프로필, 첨부파일 포함)
    FileBookPostDTO getPostWithFiles(Long id);

    public List<BookPostVO> getTopPosts();

    public void insertTopBookPosts(MonthlyBookPostVO monthlyBookPostVO);

    public List<MonthlyBookPostVO> getMonthlyBookPosts(Pagination pagination);

    public Optional<MonthlyBookPostVO> getBestPost();

    public void insertBestPost(MonthlyBookPostVO monthlyBookPostVO);

    public int countAllBookPost();

    public int countTopPosts();

    public BookPostVO getBookPostById(Long id);

    public void updateBookPostStatus(Long postId);

    public List<ReceiverInfoDTO> getAllReceiverPost(Pagination pagination);

    public int countAllReceiverPost(Pagination pagination);

    public ReceiverDTO getReceiverById(Long id);

    public List<BookDonateDTO> getDonateBooks();

    public List<BookDonateDTO> getAllDonateBooks();

    public List<ReceiverPostDTO> getReceiverPosts();

    public ReceiverPostDTO getReceiverPostById(Long id);

    public List<DonateCertPostDTO> getAllDonateCerts();

    public DonateCertPostDTO getDonateCertById(Long id);

    public List<DiscussionPostDTO> getAllDiscussions();

    public DiscussionPostDTO getDiscussionById(Long id);

    public void insertDiscussion(DiscussionDTO discussionDTO);

    public default PostVO toDiscussionVO(Long memberId){
        return PostVO.builder().postType(PostType.DISCUSSION).memberId(memberId).build();
    }

    public void insertPost(PostVO postVO);
}

