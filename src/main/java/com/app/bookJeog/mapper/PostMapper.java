package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    public List<BookPostVO> selectAllBookPost(Pagination pagination);

    public List<DiscussionVO> selectAllDiscussionPost(Pagination pagination);

    public int countAllDiscussionPost(Pagination pagination);

    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostDTO> selectThisAllBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn);

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
}

