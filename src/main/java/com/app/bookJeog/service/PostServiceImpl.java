package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.mapper.PostMapper;
import com.app.bookJeog.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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


}
