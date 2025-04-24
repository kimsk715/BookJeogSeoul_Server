package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookDonateDTO;
import com.app.bookJeog.domain.dto.BookDonateMemberDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookDonateVO;

import java.util.List;

public interface BookDonateService {

    public List<BookDonateMemberDTO> getAllDonation(Pagination pagination);

    public int countAllDonation(Pagination pagination);

    public default BookDonateDTO toBookDonateDTO(BookDonateVO bookDonateVO) {
        BookDonateDTO bookDonateDTO = new BookDonateDTO();
        if(bookDonateVO != null) {
            bookDonateDTO.setId(bookDonateVO.getId());
            bookDonateDTO.setMemberId(bookDonateVO.getMemberId());
            bookDonateDTO.setBookReceivedStatus(bookDonateVO.getBookReceivedStatus());
            bookDonateDTO.setBookIsbn(bookDonateVO.getBookIsbn());
            bookDonateDTO.setBookTitle(bookDonateVO.getBookTitle());
            bookDonateDTO.setCreatedDate(bookDonateVO.getCreatedDate());
            bookDonateDTO.setUpdatedDate(bookDonateVO.getUpdatedDate());
        }
        return bookDonateDTO;
    }

    public BookDonateVO getDonation(Long id);

    public void updateStatus(Long id);

    public void cancelStatus(Long id);
}
