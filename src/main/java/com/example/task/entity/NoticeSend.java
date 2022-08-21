package com.example.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 公告发送表
 * @TableName t_notice_send
 */
@Data
public class NoticeSend implements Serializable {
    /**
     * 这张表的主键
     */
    private Long id;

    /**
     * 公告发送人ID
     */
    private Long senderId;

    /**
     * 公告接收者ID（某个人的ID）
     */
    private Long receiverId;

    /**
     * 公告ID
     */
    private Long noticeId;

    /**
     * 公告发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 发送者视角公告的状态/0已发送/1删除
     */
    private Integer senderStatus;

    /**
     * 接收者视角公告的状态/0已接受/1删除
     */
    private Integer receiverStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}