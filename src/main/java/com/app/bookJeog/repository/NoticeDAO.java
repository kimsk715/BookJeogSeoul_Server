package com.app.bookJeog.repository;

import com.app.bookJeog.domain.dto.Pagination;
import com.app.bookJeog.domain.vo.NoticeVO;
import com.app.bookJeog.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDAO {
    private final NoticeMapper noticeMapper;

    public List<NoticeVO> findAllNotice(Pagination pagination){
        return noticeMapper.selectAllNotice(pagination);
    }

    public int countAllNotice(Pagination pagination){
        return noticeMapper.countAllNotice(pagination);
    }

    public NoticeVO selectNoticeById(Long id){
        return noticeMapper.selectNoticeById(id);
    }

    public void updateNotice(NoticeVO noticeVO){
        noticeMapper.updateNotice(noticeVO);
    }

    public void insertNotice(NoticeVO noticeVO){
        noticeMapper.insertNotice(noticeVO);
    }

}
