package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import com.app.bookJeog.mapper.BookMapper;
import com.app.bookJeog.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final PostMapper postMapper ;

    public List<BookPostVO> findAllBookPost(Pagination pagination) {
        return postMapper.selectAllBookPost(pagination);
    }

    public List<DiscussionVO> findAllDiscussionPost(Pagination pagination){
        return postMapper.selectAllDiscussionPost(pagination);
    }

    public int countAllDiscussionPost(Pagination pagination){
        return postMapper.countAllDiscussionPost(pagination);
    }
}
