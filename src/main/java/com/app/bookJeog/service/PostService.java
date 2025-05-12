package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.enumeration.PostType;
import com.app.bookJeog.domain.vo.*;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import com.app.bookJeog.domain.vo.ReceiverVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostService {

    // 게시물 id 로 memberId 찾기
    Optional<PostVO> selectMemberIdByPostId(Long postId);


    //  전체 독후감 목록
    public List<BookPostVO> getAllBookPost(Pagination pagination);

    //  화면에 나올 정보를 모아놓은 DTO 로 변환하는 메소드
    public default BookPostDTO toBookPostDTO(BookPostVO bookPostVO) {
        BookPostDTO bookPostDTO = new BookPostDTO();
        if (bookPostVO != null) {
            bookPostDTO.setId(bookPostVO.getId());
            bookPostDTO.setBookPostTitle(bookPostVO.getBookPostTitle());
            bookPostDTO.setBookPostText(bookPostVO.getBookPostText());
            bookPostDTO.setBookPostIsPublic(bookPostVO.getBookPostIsPublic());
            bookPostDTO.setBookPostStartDate(bookPostVO.getBookPostStartDate());
            bookPostDTO.setBookPostEndDate(bookPostVO.getBookPostEndDate());
            if (bookPostVO.getBookId() != null) {
                bookPostDTO.setBookId(bookPostVO.getBookId());
            }
            if (bookPostVO.getBookIsbn() != null) {
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
        if (discussionVO != null) {
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

    public PostVO insertPost(PostVO postVO);

    public void setTopReceiver(Long receiverId);
    // 독후감 피드 전체조회(무한스크롤)
    public List<FileBookPostDTO> findAllBookPostFeed(int offset);

    // 독후감 팔로잉 전체조회(무한스크롤)
    public List<FileBookPostDTO> findFollowBookPostFeed(Long loginMemberId, int offset);

    // 독후감 작성...
    public Long write(FileBookPostDTO fileBookPostDTO, List<MultipartFile> files);

    public void setDonateCertPost(DonateCertVO donateCertVO);

    // 독후감 수정
    public void setBookPost(FileBookPostDTO fileBookPostDTO, List<Long> deletedFileIds);

    // 독후감 수정을 위한 정보 조회
    FileBookPostDTO findWrittenBookPost(Long bookPostId);

    // 독후감 수정을 위한 첨부파일 조회
    public List<BookPostFileDTO> findWrittenBookPostFiles(Long bookPostId);
    public void setReceiverPost(ReceiverVO receiverVO);

    public void updateReceiverPost(ReceiverDTO receiverDTO);

    public void updateDonateCertPost(DonateCertDTO donateCertDTO);

    // 내가 작성한 독후감 조회
    List<FileBookPostDTO> getMyBookPosts(Long memberId, String sort, int offset);

    // 독후감 삭제
    public void deleteBookPost(Long bookPostId);

    public String getPostType(Long postId);

    public List<MonthlyBookPostDTO> getMonthlyBookPosts();

    public default MonthlyBookPostDTO toMonthlyBookPostDTO(MonthlyBookPostVO monthlyBookPostVO) {
        if(monthlyBookPostVO != null){
            MonthlyBookPostDTO postDTO = new MonthlyBookPostDTO();
            postDTO.setId(monthlyBookPostVO.getId());
            postDTO.setBookPostId(monthlyBookPostVO.getBookPostId());
            postDTO.setBookPostVoteCount(monthlyBookPostVO.getBookPostVoteCount());
            postDTO.setBookPostTitle(monthlyBookPostVO.getBookPostTitle());
            postDTO.setBookPostText(monthlyBookPostVO.getBookPostText());
            postDTO.setBookPostLikeCount(monthlyBookPostVO.getBookPostLikeCount());
            postDTO.setCreatedDate(monthlyBookPostVO.getCreatedDate());
            postDTO.setUpdatedDate(monthlyBookPostVO.getUpdatedDate());
            return postDTO;

        }
        return null;
    }
}

