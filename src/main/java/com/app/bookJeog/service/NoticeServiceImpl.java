package com.app.bookJeog.service;

import com.app.bookJeog.domain.dto.EventDTO;
import com.app.bookJeog.domain.dto.NoticeDTO;
import com.app.bookJeog.domain.dto.NoticeInfoDTO;
import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.EventVO;
import com.app.bookJeog.domain.vo.NoticeVO;
import com.app.bookJeog.repository.NoticeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {
    private final NoticeDAO noticeDAO;

    @Override
    public List<NoticeDTO> getAllNotice(Pagination pagination) {
        List<NoticeVO> tempList = noticeDAO.findAllNotice(pagination);
        List<NoticeDTO> noticeDTOList = new ArrayList<NoticeDTO>();
        for(NoticeVO noticeVO : tempList){
            NoticeDTO noticeDTO = new NoticeDTO();
            noticeDTO.setId(noticeVO.getId());
            noticeDTO.setNoticeText(noticeVO.getNoticeText());
            noticeDTO.setNoticeTitle(noticeVO.getNoticeTitle());
            noticeDTO.setCreatedDate(noticeVO.getCreatedDate());
            noticeDTO.setUpdatedDate(noticeVO.getUpdatedDate());
            noticeDTOList.add(noticeDTO);
        }
        return noticeDTOList;
    }

    @Override
    public int countAllNotice(Pagination pagination) {
        return noticeDAO.countAllNotice(pagination);
    }

    @Override
    public NoticeVO selectNoticeById(Long id) {
        return noticeDAO.selectNoticeById(id);
    }

    @Override
    public void updateNotice(NoticeVO noticeVO) {
        noticeDAO.updateNotice(noticeVO);
    }

    @Override
    public NoticeVO insertNotice(NoticeVO noticeVO) {
        noticeDAO.insertNotice(noticeVO);
        return noticeVO;
    }

    @Override
    public List<NoticeInfoDTO> getAllNoticeClient() {
        List<NoticeInfoDTO> noticeList = new ArrayList<>();
        for(NoticeVO noticeVO : noticeDAO.findAllNoticeClient()){
            NoticeInfoDTO noticeInfoDTO = new NoticeInfoDTO();
            noticeInfoDTO.setNoticeVO(noticeVO);
            noticeList.add(noticeInfoDTO);
            // 이미지 추가 예정.
        }
        return noticeList;
    }

    @Override
    public List<EventDTO> getAllEvent() {
       List<EventVO> eventVOs = noticeDAO.findAllEvent();
       List<EventDTO> eventDTOList = new ArrayList<>();
       for(EventVO eventVO : eventVOs){
           EventDTO eventDTO = toEventDTO(eventVO);
           eventDTOList.add(eventDTO);
       }
        return eventDTOList;
    }

    @Override
    public EventDTO getEventById(Long id) {
        return toEventDTO(noticeDAO.findEventById(id));
    }

    @Override
    public void setEvent(EventVO eventVO) {
        noticeDAO.setEvent(eventVO);
    }


}
