package com.app.bookJeog.repository;


import com.app.bookJeog.domain.dto.BookPostMemberDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.mapper.BookMapper;

import com.app.bookJeog.domain.dto.BookPostDTO;

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
    public ArrayList<BookPostMemberDTO> selectThisBookPosts(Long isbn){
        return postMapper.selectThisBookPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostMemberDTO> selectThisBookAllPosts(Long isbn, int offset){
        return postMapper.selectThisAllBookPosts(isbn, offset);
    };

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn){
        return postMapper.selectBookAllPostsCount(isbn);
    };

    // 검색한 검색어에 맞는 독후감 통합검색 조회
    public ArrayList<BookPostMemberDTO> searchBookPosts(String keyword){
        return postMapper.searchBookPosts(keyword);
    };

    // 검색한 검색어에 맞는 독후감 통합검색 개수 조회
    public int selectBookPostsCount(String keyword){
        return postMapper.selectBookPostsCount(keyword);
    }
}
