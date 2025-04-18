package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.BookInfoVO;
import com.app.bookJeog.domain.vo.SelectedBookVO;
import com.app.bookJeog.domain.vo.TempSelectedBookVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public void insertTempSelectedBook(Long isbn);

    public List<TempSelectedBookVO> selectTempSelectedBook();

    public void insertSelectedBook(SelectedBookVO selectedBookVO);
}
