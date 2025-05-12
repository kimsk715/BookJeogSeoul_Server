package com.app.bookJeog.mapper;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.EventVO;
import com.app.bookJeog.domain.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    // 제목주석, 메소드 기능 간단설명 후에 2칸띄기
    public List<NoticeVO> selectAllNotice(Pagination pagination);

    public int countAllNotice(Pagination pagination);

    public NoticeVO selectNoticeById(Long id);

    public void updateNotice(NoticeVO noticeVO);

    public void insertNotice(NoticeVO noticeVO);

    public List<NoticeVO> selectAllNoticeClient();

    public List<EventVO> selectAllEvents();

    public EventVO selectEventById(Long id);
}
