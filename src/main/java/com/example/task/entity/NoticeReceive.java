package com.example.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 公告接收表
 * @TableName t_notice_receive
 */
@Data
public class NoticeReceive implements Serializable {
    /**
     * 这张表的主键
     */
    private Long id;

    /**
     * 公告ID
     */
    private Long noticeId;

    /**
     * 接收者ID
     */
    private Long receiverId;

    /**
     * 对于接收者这条公告的状态/0未读/1已读
     */
    private Integer noticeStatus;

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