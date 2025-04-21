package com.app.bookJeog.mapper;


import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostMapper {
        public List<BookPostVO> selectAllBookPost(Pagination pagination);

        public List<DiscussionVO> selectAllDiscussionPost(Pagination pagination);

        public int countAllDiscussionPost(Pagination pagination);

    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostDTO> selectThisAllBookPosts(Long isbn, int offset);

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn);

}

