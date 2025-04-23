package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.BookDonateDTO;
import com.app.bookJeog.domain.dto.BookDonateMemberDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.BookDonateVO;
import com.app.bookJeog.repository.BookDonateDAO;
import com.app.bookJeog.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BookDonateServiceImpl implements BookDonateService {

    private final BookDonateDAO bookDonateDAO;
    private final MemberDAO memberDAO;

    @Override
    public List<BookDonateMemberDTO> getAllDonation(Pagination pagination) {
        List<BookDonateVO> tempList = bookDonateDAO.findAllDonation(pagination);
        List<BookDonateMemberDTO> bookDonateDTOList = new ArrayList<>();
        for(BookDonateVO bookDonateVO : tempList){
            BookDonateDTO bookDonateDTO = new BookDonateDTO();
            BookDonateMemberDTO bookDonateMemberDTO = new BookDonateMemberDTO();
            bookDonateDTO.setId(bookDonateVO.getId());
            bookDonateDTO.setBookIsbn(bookDonateVO.getBookIsbn());
            bookDonateDTO.setBookTitle(bookDonateVO.getBookTitle());
            bookDonateDTO.setBookReceivedStatus(bookDonateVO.getBookReceivedStatus());
            bookDonateDTO.setMemberId(bookDonateVO.getMemberId());
            bookDonateDTO.setCreatedDate(bookDonateVO.getCreatedDate());
            bookDonateDTO.setUpdatedDate(bookDonateVO.getUpdatedDate());
            bookDonateMemberDTO.setBookDonateDTO(bookDonateDTO);
            bookDonateMemberDTO.setMemberName(memberDAO.findPersonalMemberById(bookDonateVO.getMemberId()).getMemberName());
            bookDonateDTOList.add(bookDonateMemberDTO);
        }
        return bookDonateDTOList;
    }

    @Override
    public int countAllDonation(Pagination pagination) {
        return bookDonateDAO.countAllDonation(pagination);
    }

    @Override
    public BookDonateVO getDonation(Long id) {
        return bookDonateDAO.findDonation(id);
    }

    @Override
    public void updateStatus(Long id) {
        bookDonateDAO.updateStatus(id);
    }

    @Override
    public void cancelStatus(Long id) {
        bookDonateDAO.cancelStatus(id);
    }
}
