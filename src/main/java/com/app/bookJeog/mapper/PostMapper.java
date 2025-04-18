package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.BookPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PostMapper {
    //   이 책으로 작성한 독후감 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn);
}
