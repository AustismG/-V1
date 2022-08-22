package com.example.task.service;

import com.example.task.vo.NoticeVO;
import com.example.task.vo.PageResult;

/**
* @author 123
* @description 针对表【t_notice_receive(公告接收表)】的数据库操作Service
* @createDate 2022-08-20 16:52:48
*/
public interface NoticeReceiveService{


    void updateNoticeStatus(Long noticeId, Integer noticeStatus, Long receiverId);

    void logicalDelete(Long noticeId,Long receiverId);

    PageResult<NoticeVO> getNotice(Long receiverId, Long noticeStatus, Integer current, Integer pageSize);
}
