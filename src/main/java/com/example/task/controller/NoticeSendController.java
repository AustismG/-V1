package com.example.task.controller;

import com.example.task.service.NoticeSendService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/sysytem/admins")
public class NoticeSendController {
    @Autowired
    private NoticeSendService noticeSendService;

    /**
     * @Author Gzy
     * @Description 管理员逻辑删除公告
     * @Param [senderStatus, noticeId]
     * @return void
     * @is_Available 测试未通过!       
     **/
    @PutMapping("/notices/{senderStatus}/{noticeId}")
    public void updateStatus(@PathVariable Integer senderStatus,
                             @PathVariable Long noticeId) {

        noticeSendService.updateStatus(noticeId, senderStatus);
    }

}

