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
    @NotBlank(message = "公告标题不能为空")
    private String title;

    /**
     * 公告内容
     */
    @NotBlank(message = "公告内容不能为空")
    private String content;

    /**
     * 发布ID
     */
    @NotNull(message = "发布者ID不能为空")
    private Long publisherId;

    /**
     * 发布时间
     */
    @NotBlank(message = "发布时间不能为空")
    private String publishTime;
}
