package com.app.bookJeog.service;


import com.app.bookJeog.domain.dto.NoticeDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.dto.EventDTO;
import com.app.bookJeog.domain.dto.NoticeDTO;
import com.app.bookJeog.domain.dto.NoticeInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.EventVO;
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

    public NoticeVO insertNotice(NoticeVO noticeVO);

    public List<NoticeInfoDTO> getAllNoticeClient();

    public List<EventDTO> getAllEvent();

    public EventDTO getEventById(Long id);

    public default EventDTO toEventDTO(EventVO eventVO){
        EventDTO eventDTO = new EventDTO();
        if(eventVO != null){
            eventDTO.setId(eventVO.getId());
            eventDTO.setYear(eventVO.getYear());
            eventDTO.setMonth(eventVO.getMonth());
            eventDTO.setEventType(eventVO.getEventType());
        }
        return eventDTO;


    }
    public void setEvent(EventVO eventVO);
}
