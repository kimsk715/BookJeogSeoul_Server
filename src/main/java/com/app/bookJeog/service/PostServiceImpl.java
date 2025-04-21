package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.domain.vo.MonthlyBookPostVO;
import com.app.bookJeog.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;

    @Override
    public List<BookPostVO> getAllBookPost(Pagination pagination) {

        return postDAO.findAllBookPost(pagination);
    }

    @Override
    public List<DiscussionVO> getAllDiscussionPost(Pagination pagination) {

        return postDAO.findAllDiscussionPost(pagination);
    }

    @Override
    public int countAllDiscussionPost(Pagination pagination) {
        return postDAO.countAllDiscussionPost(pagination);
    }


    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn){
        return postDAO.selectThisBookPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostDTO> selectThisBookAllPosts(Long isbn){
        return postDAO.selectThisBookAllPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn) {
        return postDAO.selectBookAllPostsCount(isbn);
    }

    // top20 독후감 선정
    @Override
    public List<BookPostVO> getTopPosts() {
        return postDAO.findTopPosts();
    }

    // top20 독후감 테이블에 저장
    @Override
    public void insertTopBookPosts(MonthlyBookPostVO monthlyBookPostVO) {
        postDAO.insertTopBookPosts(monthlyBookPostVO);
    }

    // top20
    @Override
    public List<MonthlyBookPostVO> getMonthlyBookPosts(Pagination pagination) {
        return postDAO.findMonthlyBookPosts(pagination);
    }

    @Override
    public Optional<MonthlyBookPostVO> getBestPost() {
        return postDAO.findBestPost();
    }

    @Override
    public void insertBestPost(MonthlyBookPostVO monthlyBookPostVO) {
        postDAO.insertBestPost(monthlyBookPostVO);
    }

    @Override
    public int countAllBookPost() {
        return postDAO.countAllBookPost();
    }


}
