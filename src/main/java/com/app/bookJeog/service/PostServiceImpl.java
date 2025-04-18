package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;

    //   이 책으로 작성한 독후감 조회
    public ArrayList<BookPostDTO> selectThisBookPosts(Long isbn){
        return postDAO.selectThisBookPosts(isbn);
    };
}
