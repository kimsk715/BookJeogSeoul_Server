package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostDTO;

import java.util.ArrayList;

public interface PostService {
    //   이 책으로 작성한 독후감 일부 조회
    ArrayList<BookPostDTO> selectThisBookPosts(Long isbn);

    //   이 책으로 작성한 독후감 전체 조회
    ArrayList<BookPostDTO> selectThisBookAllPosts(Long isbn, int offset);

    //   이 책으로 작성한 독후감 전체 개수 조회
    int selectBookAllPostsCount(Long isbn);
}
