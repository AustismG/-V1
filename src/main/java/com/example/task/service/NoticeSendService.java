package com.example.task.service;

/**
* @author 123
* @description 针对表【t_notice_send(公告发送表)】的数据库操作Service
* @createDate 2022-08-20 16:52:53
*/
public interface NoticeSendService {

    void updateStatus(Long noticeId, Integer senderStatus);
}
