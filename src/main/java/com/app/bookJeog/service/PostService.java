package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostDTO;

import java.util.ArrayList;

public interface PostService {
    //   이 책으로 작성한 독후감 조회
    ArrayList<BookPostDTO> selectThisBookPosts(Long isbn);
}
