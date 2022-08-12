package com.example.task.param.NoticeParam;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 */
@Data
public class AdminModifyNoticeParam {
    /**
     * 公告ID
     */
    @NotNull(message = "公告ID不能为空")
    private Long noticeId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;
}
