package com.app.bookJeog.service;
import com.app.bookJeog.domain.vo.BookInfoVO;
import java.util.List;

public interface BookService {
    public List<BookInfoVO> getAllBook();

//    api 요청 코드를 default 메소드로 인터페이스에 분리해서 구현
//    ex) 신착도서 정보 요청 코드를 bookservice 에 넣기
}
