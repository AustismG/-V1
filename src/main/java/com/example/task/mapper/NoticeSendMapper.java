package com.example.task.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 123
* @description 针对表【t_notice_send(公告发送表)】的数据库操作Mapper
* @createDate 2022-08-20 16:52:53
* @Entity com.example.task.entity.NoticeSend
*/
@Mapper
public interface NoticeSendMapper{

    void insert(@Param("publisherId") Long publisherId,
                @Param("receiverId") Long receiverId,
                @Param("noticeId") Long noticeId,
                @Param("currentTime") String currentTime);
}




