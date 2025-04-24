package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookDonateVO;
import com.app.bookJeog.mapper.BookDonateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDonateDAO {
    private final BookDonateMapper bookDonateMapper;

    public List<BookDonateVO> findAllDonation(Pagination pagination) {
        return bookDonateMapper.selectAllDonation(pagination);
    }

    public int countAllDonation(Pagination pagination) {
        return bookDonateMapper.countAllDonation(pagination);
    }

    public BookDonateVO findDonation(Long id) {
        return bookDonateMapper.selectDonation(id);
    }

    public void updateStatus(Long id)
    {
        bookDonateMapper.updateStatus(id);
    }

    public void cancelStatus(Long id){
        bookDonateMapper.cancelStatus(id);
    }
}
