package com.app.bookJeog.repository;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import com.app.bookJeog.domain.vo.PostVO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.*;


import com.app.bookJeog.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final PostMapper postMapper ;

    public List<BookPostVO> findAllBookPost(Pagination pagination) {
        return postMapper.selectAllBookPost(pagination);
    }

    public List<DiscussionVO> findAllDiscussionPost(Pagination pagination){
        return postMapper.selectAllDiscussionPost(pagination);
    }

    public int countAllDiscussionPost(Pagination pagination){
        return postMapper.countAllDiscussionPost(pagination);
    }


    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostMemberDTO> findThisBookPosts(Long isbn){
        return postMapper.selectThisBookPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostMemberDTO> findThisBookAllPosts(Long isbn, int offset){
        return postMapper.selectThisAllBookPosts(isbn, offset);
    };

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int findBookAllPostsCount(Long isbn){
        return postMapper.selectBookAllPostsCount(isbn);
    };

    // 검색한 검색어에 맞는 독후감 통합검색 조회
    public ArrayList<BookPostMemberDTO> searchBookPosts(String keyword){
        return postMapper.searchBookPosts(keyword);
    };

    // 검색한 검색어에 맞는 독후감 통합검색 개수 조회
    public int findBookPostsCount(String keyword){
        return postMapper.selectBookPostsCount(keyword);
    }

    // 검색한 검색어에 맞는 독후감 무한스크롤로 출력
    public ArrayList<BookPostMemberDTO> findAllBooks(String keyword, int offset, String sortType){
        return postMapper.selectAllBooks(keyword, offset, sortType);
    };

    // 검색한 검색어에 맞는 독후감 전체 개수
    public int findAllBooksCount(String keyword){
        return postMapper.selectAllBooksCount(keyword);
    };

    // 검색한 검색어에 맞는 토론글 통합검색 조회
    public List<DiscussionPostDTO> searchDiscussions(String keyword){
        return postMapper.searchDiscussions(keyword);
    };

    // 검색한 검색어에 맞는 토론글 통합검색 개수 조회
    public int findAllDiscussionCount(String keyword){
        return postMapper.selectAllDiscussionCount(keyword);
    };

    // 검색한 검색어에 맞는 토론글 무한스크롤로 출력
    public List<DiscussionPostDTO> findAllDiscussion(String keyword, int offset, String sortType){
        return postMapper.selectAllDiscussion(keyword, offset, sortType);
    };

    // 검색한 검색어에 맞는 기부글 통합검색 조회
    public List<ReceiverPostDTO> searchReceivers(String keyword){
        return postMapper.searchReceivers(keyword);
    };

    // 검색한 검색어에 맞는 기부글 통합검색 개수 조회
    public int findAllReceiverCount(String keyword){
        return postMapper.selectAllReceiverCount(keyword);
    };

    // 검색한 검색어에 맞는 기부글 무한스크롤로 출력
    public List<ReceiverPostDTO> findAllReceivers(String keyword, int offset, String sortType){
        return postMapper.selectAllReceivers(keyword, offset, sortType);
    };

    // 특정 독후감의 상세 정보와 회원 프로필 이미지 조회
    public FileBookPostDTO findPostDetail(Long id) {
        return postMapper.selectBookPostWithFiles(id);
    }

    //  특정 독후감의 첨부파일 목록 조회
    public List<BookPostFileDTO> findPostFiles(Long postId) {
        return postMapper.selectBookPostFiles(postId);
    }

    public List<BookPostVO> findTopPosts(){
        return postMapper.selectTopPosts();
    }

    public void insertTopBookPosts(MonthlyBookPostVO monthlyBookPostVO){
        postMapper.insertTopBookPosts(monthlyBookPostVO);
    }

    public List<MonthlyBookPostVO> findMonthlyBookPosts(Pagination pagination){
        return postMapper.selectMonthlyBookPosts(pagination);
    }

    public Optional<MonthlyBookPostVO> findBestPost(){
        return postMapper.selectBestPost();
    }

    public void insertBestPost(MonthlyBookPostVO monthlyBookPostVO){
        postMapper.insertBestPost(monthlyBookPostVO);
    }

    public int countAllBookPost(){
        return postMapper.countAllBookPost();
    }

    public int countTopPosts(){
        return postMapper.countTopPosts();
    }

    public BookPostVO findBookPostById(Long id){
        return postMapper.selectBookPostById(id);
    }
    public void updateBookPostStatus(Long postId) {
        postMapper.updateBookPostStatus(postId);
    }

    public PostVO findPostById(Long id){
        return postMapper.selectPostById(id);
    }

    public List<ReceiverVO> findAllReceiverPost(Pagination pagination){
        return postMapper.selectAllReceiverPost(pagination);
    }

    public int countAllReceiverPost(Pagination pagination){
        return postMapper.countAllReceiverPost(pagination);
    }

    public ReceiverVO findReceiverById(Long id){
        return postMapper.selectReceiverById(id);
    }

    public List<BookDonateVO> findDonatedBooks(){
        return postMapper.selectDonatedBooks();
    }

    public List<BookDonateVO> findAllDonatedBooks(){
        return postMapper.selectAllDonatedBooks();
    }

    public List<ReceiverVO> findAllReceivers(){
        return postMapper.selectAllReceiverPosts();
    }

    public ReceiverVO findReceiverPostById(Long id){
        return postMapper.selectReceiverPostById(id);
    }

    public List<DonateCertVO> findAllDonatedCerts(){
        return postMapper.selectAllDonateCerts();
    }

    public DonateCertVO findDonateCertById(Long id){
        return postMapper.selectDonateCertById(id);
    }

    public List<DiscussionVO> findAllDiscussions(){
        return postMapper.selectAllDiscussions();
    }

    public DiscussionVO findDiscussionById(Long id){
        return postMapper.selectDiscussionById(id);
    }

    public void insertDiscussion(DiscussionVO discussionVO){
        postMapper.insertDiscussionPost(discussionVO);
    }

    public void insertPost(PostVO postVO){
        postMapper.insertPost(postVO);
    }

    public void setTopReceiver(Long receiverId){
        postMapper.updateTopReceiver(receiverId);
    }

    // 독후감 피드 전체조회(무한스크롤)
    public List<FileBookPostDTO> findAllBookPostFeed(int offset){
        return postMapper.selectAllBookPostFeed(offset);
    };

    // 독후감 팔로잉 전체조회(무한스크롤)
    public List<FileBookPostDTO> findFollowBookPostFeed(Long loginMemberId, int offset){
        return postMapper.selectFollowBookPostFeed(loginMemberId, offset);
    };

    // 일반도서 독후감 작성
    public void insertPostBook(PostVO postVO){postMapper.insertPostBook(postVO);};
    public void insertBookPost(BookPostVO bookPostVO){postMapper.insertBookPost(bookPostVO);};

    // 선정도서 독후감 작성
    public void insertSelectedBookPost(SelectedBookPostVO selectedBookPostVO){postMapper.insertSelectedBookPost(selectedBookPostVO);};

    public void insertDonateCertPost(DonateCertVO donateCertVO){
        postMapper.insertDonateCertPost(donateCertVO);
    }

    // 독후감 수정을 위한 데이터 조회
    public FileBookPostDTO findWrittenBookPost(Long bookPostId){ return postMapper.selectWrittenBookPost(bookPostId); };
    public FileBookPostDTO findWrittenSelectedPost(Long bookPostId) { return postMapper.selectWrittenSelectedPost(bookPostId); };

    // 독후감 내용 수정
    public void setPost(PostVO postVO){postMapper.updatePost(postVO);};
    public void setBookPost(BookPostVO bookPostVO){postMapper.updateBookPost(bookPostVO);};
    public void setSelectedBookPost(SelectedBookPostVO selectedBookPostVO){postMapper.updateSelectedBookPost(selectedBookPostVO);};
    public void insertReceiverPost(ReceiverVO receiverVO){
        postMapper.insertReceiverPost(receiverVO);
    }

    public void updateReceiverPost(ReceiverVO receiverVO){
        postMapper.updateReceiverPost(receiverVO);
    }

    public void updateDonateCertPost(DonateCertVO donateCertVO){
        postMapper.updateDonateCertPost(donateCertVO);
    }
}
