package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {
    public void insertTempSelectedBook(Long isbn);

    public List<TempSelectedBookVO> selectTempSelectedBook();

    public void insertSelectedBook(SelectedBookVO selectedBookVO);


    // 인기최다 도서
    public List<MemberHistoryVO> selectTopViewBook();


    // 관리자 선정도서
    public List<SelectedBookVO> selectAdminSuggestBooks();


    // 인기 독후감
    public List<BookPostVO> selectTopBookPost();
     // 선정 도서 여부 조회
    public Optional<SelectedBookVO> selectSelectedBooks(Long bookIsbn);

    // 전체 도서 isbn과 줄거리 가져오기
    public List<BookVO> selectIsbnAndSummary();

    // 최근 조회한 도서 10개 줄거리와 함께
    public String selectBookSummaryToString(Long memberId);
}
