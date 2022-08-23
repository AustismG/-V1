package com.example.task.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 */
@Data
public class NoticeVO {
    /**
    * 公告标题
    */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布ID
     */
    private String publisherName;

    /**
     * 发布时间
     */
    private String createTime;
}
