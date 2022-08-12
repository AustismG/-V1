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
     * 发布公告者ID
     */
    private Long publisherId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 公告内容
     */
    private byte[] content;

    private static final long serialVersionUID = 1L;
}