package com.app.bookJeog.repository;


import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import com.app.bookJeog.mapper.BookMapper;

import com.app.bookJeog.domain.dto.BookPostDTO;

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
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn){
        return postMapper.selectThisBookPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostDTO> selectThisBookAllPosts(Long isbn){
        return postMapper.selectThisAllBookPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn){
        return postMapper.selectBookAllPostsCount(isbn);
    };


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
}
