package com.app.bookJeog.repository;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.mapper.BookMapper;

import com.app.bookJeog.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.ArrayList;


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
}
