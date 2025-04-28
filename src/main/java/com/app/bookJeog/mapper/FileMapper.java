package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.vo.BookPostFileVO;
import com.app.bookJeog.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    // 독후감 첨부파일 넣기
    public void insertFiles(FileVO fileVO);
    public void insertBookPostFiles(BookPostFileVO bookPostFileVO);
}
