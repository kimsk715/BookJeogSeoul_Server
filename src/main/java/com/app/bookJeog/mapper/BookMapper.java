package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}
