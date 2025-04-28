package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    public List<BookPostVO> selectAllBookPost(Pagination pagination);

    public List<DiscussionVO> selectAllDiscussionPost(Pagination pagination);

    public int countAllDiscussionPost(Pagination pagination);

    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostMemberDTO> selectThisBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostMemberDTO> selectThisAllBookPosts(@Param("bookIsbn") Long isbn,
                                                               @Param("offset") int offset);

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn);

    // 검색한 검색어에 맞는 독후감 통합검색 조회
    public ArrayList<BookPostMemberDTO> searchBookPosts(String keyword);

    // 검색한 검색어에 맞는 독후감 통합검색 개수 조회
    public int selectBookPostsCount(String keyword);

    // 검색한 검색어에 맞는 독후감 무한스크롤로 출력
    public ArrayList<BookPostMemberDTO> selectAllBooks(String keyword, int offset, String sortType);

    // 검색한 검색어에 맞는 독후감 전체 개수
    public int selectAllBooksCount(String keyword);

    // 검색한 검색어에 맞는 토론글 통합검색 조회
    public List<DiscussionPostDTO> searchDiscussions(String keyword);

    // 검색한 검색어에 맞는 토론글 통합검색 개수 조회
    public int selectAllDiscussionCount(String keyword);

    // 검색한 검색어에 맞는 토론글 무한스크롤로 출력
    public List<DiscussionPostDTO> selectAllDiscussion(String keyword, int offset, String sortType);

    // 검색한 검색어에 맞는 기부글 통합검색 조회
    public List<ReceiverPostDTO> searchReceivers(String keyword);

    // 검색한 검색어에 맞는 기부글 통합검색 개수 조회
    public int selectAllReceiverCount(String keyword);

    // 검색한 검색어에 맞는 기부글 무한스크롤로 출력
    public List<ReceiverPostDTO> selectAllReceivers(String keyword, int offset, String sortType);

    // 특정 독후감의 상세 정보와 회원 프로필 이미지 조회
    public FileBookPostDTO selectBookPostWithFiles(Long id);

    //  특정 독후감의 첨부파일 목록 조회
    public List<BookPostFileDTO> selectBookPostFiles(Long bookPostId);

    public List<BookPostVO> selectTopPosts();

    public void insertTopBookPosts(MonthlyBookPostVO monthlyBookPostVO);

    public List<MonthlyBookPostVO> selectMonthlyBookPosts(Pagination pagination);

    public Optional<MonthlyBookPostVO> selectBestPost();

    public void insertBestPost(MonthlyBookPostVO monthlyBookPostVO);

    public int countAllBookPost();

    public int countTopPosts();

    public BookPostVO selectBookPostById(Long id);

    public void updateBookPostStatus(Long postId);

    public PostVO selectPostById(Long id);

    public List<ReceiverVO> selectAllReceiverPost(Pagination pagination);

    public int countAllReceiverPost(Pagination pagination);

    public ReceiverVO selectReceiverById(Long id);

//    게시판에 나올 10개의 기부 기록
    public List<BookDonateVO> selectDonatedBooks();

    // 전체 보기에 나올 전체 기부 목록
    public List<BookDonateVO> selectAllDonatedBooks();

    // 후원대상 게시판 목록
    public List<ReceiverVO> selectAllReceiverPosts();

    // 후원대상 게시글
    public ReceiverVO selectReceiverPostById(Long id);

    // 후원 인증 게시판 조회
    public List<DonateCertVO> selectAllDonateCerts();

    // 후원 인증 게시글
    public DonateCertVO selectDonateCertById(Long id);

    // 토론 게시판 조회
    public List<DiscussionVO> selectAllDiscussions();

    // 토론 게시글
    public DiscussionVO selectDiscussionById(Long id);

    // 토론 등록
    public void insertDiscussionPost(DiscussionVO discussionVO);

    public void insertPost(PostVO postVO);

    public void updateTopReceiver(Long receiverId);
    // 독후감 피드 전체조회(무한스크롤)
    public List<FileBookPostDTO> selectAllBookPostFeed(int offset);

    // 독후감 팔로잉 전체조회(무한스크롤)
    public List<FileBookPostDTO> selectFollowBookPostFeed(Long loginMemberId, int offset);

    // 일반도서 독후감 작성
    public void insertPostBook(PostVO postVO);
    public void insertBookPost(BookPostVO bookPostVO);

    // 선정도서 독후감 작성
    public void insertSelectedBookPost(SelectedBookPostVO selectedBookPostVO);
}

