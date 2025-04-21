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

    //   이 책으로 작성한 독후감 일부 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn){
        return postMapper.selectThisBookPosts(isbn);
    };

    //   이 책으로 작성한 독후감 전체 조회
    public ArrayList<BookPostDTO> selectThisBookAllPosts(Long isbn, int offset){
        return postMapper.selectThisAllBookPosts(isbn, offset);
    };

    //   이 책으로 작성한 독후감 전체 개수 조회
    public int selectBookAllPostsCount(Long isbn){
        return postMapper.selectBookAllPostsCount(isbn);
    };
}
