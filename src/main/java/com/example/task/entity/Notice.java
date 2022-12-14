package com.example.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 公告表
 * @TableName t_notice
 */
@Data
public class Notice implements Serializable {
    /**
     * 公告ID
     */
    private Long noticeId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布公告者ID
     */
    private Long publisherId;

    /**
     * 接收者的部门ID列表
     */
    private String receiverDepIdList;

    /**
     * 公告的状态/0已发送/1草稿
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