package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.*;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

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
}

