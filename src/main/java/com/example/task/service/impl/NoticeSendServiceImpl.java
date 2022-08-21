package com.example.task.service.impl;

import com.example.task.mapper.NoticeSendMapper;
import com.example.task.service.NoticeSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 123
* @description 针对表【t_notice_send(公告发送表)】的数据库操作Service实现
* @createDate 2022-08-20 16:52:53
*/
@Service
public class NoticeSendServiceImpl implements NoticeSendService{
    @Autowired
    private NoticeSendMapper noticeSendMapper;

    @Override
    public void updateStatus(Long noticeId, Integer senderStatus) {
        noticeSendMapper.updateStatus(noticeId,senderStatus);
    }
}




