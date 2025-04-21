package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.BookPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PostMapper {
    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostDTO> selectThisAllBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn);
}
