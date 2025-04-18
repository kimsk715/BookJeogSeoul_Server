package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.BookPostDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
public class PostMapperTests {
    @Autowired
    private PostMapper postMapper;

    // 이 책으로 써진 독후감들 조회
    @Test
    public void selectThisBookPosts() {
        Long isbn = 9788984374423L;
        ArrayList<BookPostDTO> postLists = postMapper.selectThisBookPosts(isbn);
        log.info(postLists.toString());
    }

}
