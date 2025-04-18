package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ScrapMapperTests {
    @Autowired
    private FavoriteMapper favoriteMapper;

    // 스크랩 추가
    @Test
    public void insertScrap() {
        Long memberId = 1L;
        String isbn = "9788984374423";

        favoriteMapper.insertBookScrap(memberId, isbn);
    }

    // 회원의 스크랩 여부 조회
    @Test
    public void findBookScrap() {
        Long memberId = 1L;
        String isbn = "9788984374423";

        boolean alreadyScrapped =  favoriteMapper.isBookScrapped(memberId, isbn) > 0;
    
        if(!alreadyScrapped){
            log.info("스크랩 없음");
        } else {
            log.info("스크랩 있음");
        }
    }

    // 특정 책의 스크랩 개수 조회
    @Test
    public void findMemberScrap() {
        String isbn = "9788984374423";
        int scrapCount = favoriteMapper.selectThisScrapCount(isbn);
        log.info("이 책의 스크랩 수: " + scrapCount);
    }

    // 스크랩 삭제
    @Test
    public void deleteScrap() {
        Long memberId = 1L;
        String isbn = "9788984374423";

        favoriteMapper.deleteBookScrap(memberId, isbn);
    }
}
