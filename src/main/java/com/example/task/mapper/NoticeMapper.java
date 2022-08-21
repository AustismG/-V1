package com.example.task.mapper;

import com.example.task.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 123
 * @description 针对表【t_notice(公告表)】的数据库操作Mapper
 * @createDate 2022-08-11 14:28:09
 * @Entity com.example.task.entity.Notice
 */
@Mapper
public interface NoticeMapper {

    void adminInsert(@Param("title") String title,
                     @Param("content") String content,
                     @Param("receiverDepIdStr") String receiverDepIdStr,
                     @Param("noticeStatus") Integer noticeStatus,
                     @Param("publisherId") Long publisherId);

    void delete(@Param("noticeId") Long noticeId);

    void update(@Param("noticeId") Long noticeId,
                @Param("title") String title,
                @Param("content") String content);

    List<NoticeVO> getNoticeList(@Param("noticeId") Long noticeId,
                                 @Param("publisherId") Long publisherId,
                                 @Param("title") String title,
                                 @Param("content") String content,
                                 @Param("startDate") String startDate,
                                 @Param("endDate") String endDate);

    Long getNoticeIdByTitle(@Param("publisherId") Long publisherId,
                            @Param("title") String title);

    void updateNoticeStatus(@Param("noticeId") Long noticeId,
                            @Param("status") Integer status);

    void updateReceiverDepIdList(@Param("noticeId") Long noticeId,
                                 @Param("originalReceiverDepIdList") String originalReceiverDepIdList);

    String getReceiverDepIdList(@Param("noticeId") Long noticeId);
}




