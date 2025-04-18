package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final PostMapper postMapper;

    //   이 책으로 작성한 독후감 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn){
        return postMapper.selectThisBookPosts(isbn);
    };
}
