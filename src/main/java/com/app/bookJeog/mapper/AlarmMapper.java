package com.app.bookJeog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper {

    //댓글 알람
    public void insertCommentMention();

}
