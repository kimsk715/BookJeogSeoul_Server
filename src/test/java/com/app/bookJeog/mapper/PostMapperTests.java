package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.BookPostDTO;
import com.app.bookJeog.domain.dto.BookPostMemberDTO;
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
//        ArrayList<BookPostDTO> postLists = postMapper.selectThisBookPosts(isbn);
//        log.info(postLists.toString());
    }

    // 이 책으로 써진 독후감 전체개수 조회
    @Test
    public void selectThisAllBookPosts() {
        Long isbn = 9788984374423L;
        int postCount = postMapper.selectBookAllPostsCount(isbn);
        log.info("이 책의 독후감 개수: " + postCount);
    }

    // 검색한 검색어에 맞는 독후감 통합검색 조회
    @Test
    public void searchBookPosts() {
        String keyword = "북적시인";
        ArrayList<BookPostMemberDTO> resultList = postMapper.searchBookPosts(keyword);
        log.info("검색 결과: {}", resultList);
    }


}
