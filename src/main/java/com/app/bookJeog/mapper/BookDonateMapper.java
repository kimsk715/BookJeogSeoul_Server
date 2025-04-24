package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookDonateVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDonateMapper {
    public List<BookDonateVO> selectAllDonation(Pagination pagination);

    public int countAllDonation(Pagination pagination);

    public BookDonateVO selectDonation(Long id);

    public void updateStatus(Long id);

    public void cancelStatus(Long id);
}
