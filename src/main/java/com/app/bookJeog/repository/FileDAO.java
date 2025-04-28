package com.app.bookJeog.repository;

import com.app.bookJeog.domain.vo.BookPostFileVO;
import com.app.bookJeog.domain.vo.FileVO;
import com.app.bookJeog.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper ;

    // 독후감 첨부파일 넣기
    public void insertFiles(FileVO fileVO){
        fileMapper.insertFiles(fileVO);
    };
    public void insertBookPostFiles(BookPostFileVO bookPostFileVO){fileMapper.insertBookPostFiles(bookPostFileVO);};
}
