package com.app.bookJeog.service;


import com.app.bookJeog.domain.dto.NoticeDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.NoticeVO;
import java.util.List;

public interface NoticeService {
    public List<NoticeDTO> getAllNotice(Pagination pagination);

    public int countAllNotice(Pagination pagination);

    public NoticeVO selectNoticeById(Long id);

    public default NoticeDTO toNoticeDTO(NoticeVO noticeVO){
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(noticeVO.getId());
        noticeDTO.setNoticeTitle(noticeVO.getNoticeTitle());
        noticeDTO.setNoticeText(noticeVO.getNoticeText());
        noticeDTO.setCreatedDate(noticeVO.getCreatedDate());
        noticeDTO.setUpdatedDate(noticeVO.getUpdatedDate());
        return noticeDTO;
    };

    public void updateNotice(NoticeVO noticeVO);

    public void insertNotice(NoticeVO noticeVO);
}
