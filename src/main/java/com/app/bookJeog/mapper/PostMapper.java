package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookPostVO;
import com.app.bookJeog.domain.vo.DiscussionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
        public List<BookPostVO> selectAllBookPost(Pagination pagination);

        public List<DiscussionVO> selectAllDiscussionPost(Pagination pagination);

        public int countAllDiscussionPost(Pagination pagination);
}

