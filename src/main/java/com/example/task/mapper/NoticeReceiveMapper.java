package com.example.task.mapper;

import com.example.task.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 123
* @description 针对表【t_notice_receive(公告接收表)】的数据库操作Mapper
* @createDate 2022-08-20 16:52:48
* @Entity com.example.task.entity.NoticeReceive
*/

@Mapper
public interface NoticeReceiveMapper{

    void insert(@Param("noticeId") Long noticeId,
                @Param("receiverId") Long receiverId);

    void updateNoticeStatus(@Param("noticeId") Long noticeId,
                            @Param("noticeStatus") Integer noticeStatus,
                            @Param("receiverId") Long receiverId);

    void logicalDelete(@Param("noticeId") Long noticeId);

    List<NoticeVO> getNoticeReceiveList(@Param("receiverId") Long receiverId,
                                        @Param("noticeStatus") Long noticeStatus);
}




